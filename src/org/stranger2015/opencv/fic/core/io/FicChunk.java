package org.stranger2015.opencv.fic.core.io;

import ar.com.hjg.pngj.*;
import ar.com.hjg.pngj.chunks.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Deprecated
public
class FicChunk extends ChunkRaw {

    public
    FicChunk ( int len, String id, boolean alloc ) {
        super(len, id, alloc);
    }

    public
    FicChunk ( int len, byte[] idbytes, boolean alloc ) {
        super(len, idbytes, alloc);
    }

    /**
     * This example shows how to register a custom chunk.
     *
     *
     */
        // Example chunk: this stores a Java property as XML
        public static class PngChunkPROP extends PngChunkSingle {
            // ID must follow the PNG conventions: four ascii letters,
            // ID[0] : lowercase (ancillary)
            // ID[1] : lowercase if private, upppecase if public
            // ID[3] : uppercase if "safe to copy"
            public final static String ID = "prOp";

            final Properties props;

            // fill with your own "high level" properties, in this example,
            public PngChunkPROP( ImageInfo imgInfo) {
                super(ID, imgInfo);
                props = new Properties();
            }

            @Override
            public
            ChunkRaw createRawChunk() {
                // This code "serializes" your fields, according to our chunk spec
                // For other examples, see the code from other PngChunk implementations
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                try {
                    props.storeToXML(bos, "MyChunk");
                } catch (IOException e) {
                    throw new PngjException("error creating chunk", e);
                }
                ChunkRaw c = createEmptyChunk(bos.size(), true);
                System.arraycopy(bos.toByteArray(), 0, c.data, 0, c.len);
                return c;
            }

            @Override
            public void parseFromRaw(ChunkRaw c) {
                // This code "deserializes" your fields, according to our chunk spec
                // For other examples, see the code from other PngChunk implementations
                props.clear();
                try {
                    ByteArrayInputStream bis = new ByteArrayInputStream(c.data, 0, c.len);
                    props.loadFromXML(bis);
                } catch (Exception e) {
                    throw new PngjException("error creating chunk", e);
                }
            }

            @Override
            public ChunkOrderingConstraint getOrderingConstraint() {
                // change this if you don't require this chunk to be before IDAT, etc
                return ChunkOrderingConstraint.BEFORE_IDAT;
            }

            public Properties getProps() {
                return props;
            }

        }

        public static void addPropChunk(String orig, String dest, Properties p) {
            if (orig.equals(dest)) {
                throw new RuntimeException("orig == dest???");
            }
            PngReader pngr = new PngReader(new File(orig));
            PngWriter pngw = new PngWriter(new File(dest), pngr.imgInfo, true);
            System.out.println("Reading : " + pngr);
            pngw.copyChunksFrom(pngr.getChunksList(), ChunkCopyBehaviour.COPY_ALL_SAFE);
            PngChunkPROP mychunk = new PngChunkPROP(pngw.imgInfo);
            mychunk.getProps().putAll(p);
            mychunk.setPriority(true); // if we want it to be written as soon as possible
            pngw.getChunksList().queue(mychunk);
            for (int row = 0; row < pngr.imgInfo.rows; row++) {
                IImageLine l1 = pngr.readRow();
                pngw.writeRow(l1);
            }
            pngr.end();
            pngw.end();
            System.out.printf("Done. Written : %s", dest);
        }

        static class MyCustomChunkFactory extends ChunkFactory { // this could also be an anonymous class
            @Override
            protected PngChunk createEmptyChunkExtended(String id, ImageInfo imgInfo) {
                if (id.equals(PngChunkPROP.ID)) {
                    return new PngChunkPROP(imgInfo);
                }
                return super.createEmptyChunkExtended(id, imgInfo);
            }
        }

        public static void readPropChunk(String ori) {
            // to read the "unkwnon" chunk as our desired chunk, we must statically register
            // it
            PngReader pngr = new PngReader(new File(ori));
            pngr.getChunkseq().setChunkFactory(new MyCustomChunkFactory());
            System.out.printf("Reading : %s\n", pngr);
            pngr.readSkippingAllRows();
            pngr.end();
            // we know there can be at most one chunk of this type...
            PngChunk chunk = pngr.getChunksList().getById1(PngChunkPROP.ID);
            System.out.println(chunk);
            // the following would fail if we had not register the chunk
            PngChunkPROP chunkprop = (PngChunkPROP) chunk;
            System.out.println(chunkprop != null ? chunkprop.getProps() : " NO PROP CHUNK");
        }

        public static void testWrite() {
            Properties prop = new Properties();
            prop.setProperty("curtime", String.valueOf(System.currentTimeMillis()));
            prop.setProperty("home", System.getenv("HOME"));
            addPropChunk("/temp/x.png", "/temp/x2.png", prop);
        }

        public static void testRead() {
            readPropChunk("/temp/x2.png");
        }

        public static void main(String[] args) throws Exception {
            testWrite();
            testRead();
        }
    }

