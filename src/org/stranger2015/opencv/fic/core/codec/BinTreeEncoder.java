package org.stranger2015.opencv.fic.core.codec;

import org.jetbrains.annotations.Contract;
import org.stranger2015.opencv.fic.core.*;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode;
import org.stranger2015.opencv.fic.core.TreeNodeBase.TreeNode.LeafNode;
import org.stranger2015.opencv.fic.core.search.ISearchProcessor;
import org.stranger2015.opencv.fic.transform.AffineTransform;
import org.stranger2015.opencv.fic.transform.ImageTransform;
import org.stranger2015.opencv.fic.transform.ScaleTransform;
import org.stranger2015.opencv.utils.BitBuffer;

import java.lang.ref.Cleaner;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @param <N>
 * @param <A>
 * @param <G>
 */
public
class BinTreeEncoder<N extends TreeNode <N, A, G>, A extends IAddress <A>, G extends BitBuffer>
        extends Encoder <N, A, G> {

    /**
     * @param scheme
     * @param searchProcessor     //     * @param rangeSize
     * @param scaleTransform
     * @param imageBlockGenerator
     * @param comparator
     * @param fractalModel
     */
    public
    BinTreeEncoder ( EPartitionScheme scheme,
                     TreeNodeBuilder <N, A, G> nodeBuilder,
                     ISearchProcessor <N, A, G> searchProcessor,
                     ScaleTransform <A, G> scaleTransform,
                     ImageBlockGenerator <N, A, G> imageBlockGenerator,
                     IDistanceator <A> comparator,
                     Set <ImageTransform <A, G>> transforms,
                     Set <IImageFilter <A>> filters,
                     FractalModel <N, A, G> fractalModel
    ) {
        super(scheme,
                nodeBuilder,
                searchProcessor,
                scaleTransform,
                imageBlockGenerator,
                comparator,
                transforms,
                filters,
                fractalModel
        );
    }

    public
    BinTreeEncoder ( EPartitionScheme scheme, Class<?>[] paramTypes, Object[] params ) {
        super(scheme, paramTypes, params);
    }

//    /**
//     * @param scheme
//     * @param nodeBuilder
//     * @param searchProcessor
//     * @param scaleTransform
//     * @param imageBlockGenerator
//     * @param comparator
//     * @param imageTransforms
//     * @param filters
//     * @param fractalModel
//     */
//    protected
//    BinTreeEncoder ( EPartitionScheme scheme,
//                     ITreeNodeBuilder <N, A, G> nodeBuilder,
//                     ISearchProcessor <N, A, G> searchProcessor,
//                     ScaleTransform <A, G> scaleTransform,
//                     ImageBlockGenerator <N, A, G> imageBlockGenerator,
//                     IDistanceator <A> comparator,
//                     Set <ImageTransform <A, G>> imageTransforms,
//                     Set <IImageFilter <A>> filters,
//                     FractalModel <N, A, G> fractalModel ) {
//        super(scheme,
//                nodeBuilder,
//                searchProcessor,
//                scaleTransform,
//                imageBlockGenerator,
//                comparator,
//                imageTransforms,
//                filters,
//                fractalModel
//        );


    /**
     * @return
     */
    @Override
    public
    IImage <A> getInputImage () {
        return super.getInputImage();
    }

    /**
     * @param tiler
     * @param scheme
     * @param encoder
     * @param image
     * @param rangeSize
     * @param domainSize
     * @return
     */
    @Override
    public
    ImageBlockGenerator <N, A, G> createBlockGenerator ( ITiler <N, A, G> tiler,
                                                         EPartitionScheme scheme,
                                                         IEncoder <N, A, G> encoder,
                                                         IImage <A> image,
                                                         IIntSize rangeSize,
                                                         IIntSize domainSize
    ) {

        return new BinTreeImageBlockGenerator <>(
                tiler,
                scheme,
                encoder,
                image,
                rangeSize,
                domainSize
        );
    }

    /**
     * @return
     */
    @Override
    public
    ITiler <N, A, G> createTiler0 () {
        return null;
    }

    /**
     * @param node
     */
    @Override
    public
    void add ( TreeNode <N, A, G> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( LeafNode <N, A, G> node ) {

    }

    /**
     * @param node
     */
    @Override
    public
    void addLeafNode ( TreeNodeBase <N, A, G> node ) {

    }

    /**
     * @return
     */
    @Override
    public
    CompressedImage <A> getOutputImage () {
        return super.getOutputImage();
    }

    /**
     * @param inputImage
     * @return
     */
    @Override
    public final
    List <RegionOfInterest <A>> segmentImage ( IImage <A> inputImage ) throws ValueError {
        return segmentImage(inputImage, List.of());
    }

    private
    IImageBlock <A> generateBlocks ( IImage <A> inputImage ) {
//        int ss = inputImage;

        return generateBlocks0(new BinTreeNode <>(null, (IAddress <A>) new ImageBlock <>(inputImage, i, j, blockWidth, 0)));
    }

    private
    IImageBlock <A> generateBlocks0 ( BinTreeNode <N, A, G> node ) {
        return null;
    }

    @Contract(pure = true)
    private
    boolean getPartitionType ( int ss ) {
        return false;
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    IImage <A> encode ( IImage <A> image ) throws ValueError {
        image = super.encode(image);

        return image;
    }

    /**
     * @param roi
     */
    @Override
    public
    void segmentRegion ( RegionOfInterest <A> roi ) {
        List <IImageBlock <A>> list = imageBlockGenerator.generateRangeBlocks(roi);
        roi.rangeBlocks.addAll(list);
    }

    /**
     * @param image
     * @return
     */
    @Override
    public
    List <RegionOfInterest <A>> segmentImage ( IImage <A> image, List <Rectangle> bounds ) throws ValueError {
        List <RegionOfInterest <A>> list = new ArrayList <>();
        if (bounds.isEmpty()) {
            return List.of(new RegionOfInterest <>(image.getSubImage(0, 0, image.getWidth(), image.getHeight())));
        }
        imageBlockGenerator.generateRegions(image, bounds);

        return list;
    }

    /**
     * @param range
     * @param domain
     * @return
     */
    @Override
    public
    int getDistance ( IImageBlock <A> range, IImageBlock <A> domain ) {
        return super.getDistance(range, domain);
    }

    /**
     * @return
     */
    @Override
    public
    Set <ImageTransform <A, G>> getTransforms () {
        return super.getTransforms();
    }

    /**
     * x = np.asarray(x).swapaxes (axis, 0);
     * x = x[::-1, ...]
     * x = x.swapaxes(0, axis);
     *
     * @param image
     * @param axis
     * @return
     */
    @Override
    public
    IImage <A> flipAxis ( IImage <A> image, int axis ) {
        return super.flipAxis(image, axis);
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> randomTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return super.randomTransform(image, transform);
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyTransform ( IImage <A> image, ImageTransform <A, G> transform ) {
        return super.applyTransform(image, transform);
    }

    /**
     * @param image
     * @param transform
     * @return
     */
    @Override
    public
    IImage <A> applyAffineTransform ( IImage <A> image, AffineTransform <A, G> transform ) {
        return super.applyAffineTransform(image, transform);
    }

    /**
     * @param image
     * @param sourceSize
     * @param destinationSize
     * @param step
     * @return
     */
    @Override
    public
    List <IImageBlock <A>> generateAllTransformedBlocks ( IImage <A> image,
                                                          int sourceSize,
                                                          int destinationSize,
                                                          int step ) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public
    ImageBlockGenerator <N, A, G> getImageBlockGenerator () {
        return super.getImageBlockGenerator();
    }

    /**
     * @return
     */
    @Override
    public
    ScaleTransform <A, G> getScaleTransform () {
        return super.getScaleTransform();
    }

    /**
     * @return
     */
    @Override
    public
    IDistanceator <A> getComparator () {
        return super.getComparator();
    }

    /**
     * @param codec
     */
    @Override
    public
    void onCodecCreated ( ICodec <N, A, G> codec ) {
        super.onCodecCreated(codec);
    }

    /**
     * @return
     */
    @Override
    public
    int getImageSizeBase () {
        return super.getImageSizeBase();
    }

    /**
     * @param imageProcessor
     * @param filename
     */
//    @Override
    public
    void onPreprocess ( IImageProcessor <N, A, G> imageProcessor, String filename, IImage <A> image ) throws ValueError {
        super.onPreprocess(imageProcessor, filename, image);
    }

    /**
     * @param imageProcessor
     * @param inputImage
     */
    @Override
    public
    void onProcess ( IImageProcessor <N, A, G> imageProcessor, IImage <A> inputImage ) {
        super.onProcess(imageProcessor, inputImage);
    }

    /**
     * @param imageProcessor
     * @param outputImage
     */
    @Override
    public
    void onPostprocess ( IImageProcessor <N, A, G> imageProcessor, CompressedImage <A> outputImage ) {
        super.onPostprocess(imageProcessor, outputImage);
    }

    /**
     * @param instance
     */
    @Override
    public
    void onCreated ( ICodec <N, A, G> instance ) {
        super.onCreated(instance);
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the {@code hashCode} method
     *     must consistently return the same integer, provided no information
     *     used in {@code equals} comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     *     method, then calling the {@code hashCode} method on each of
     *     the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link Object#equals(Object)}
     *     method, then calling the {@code hashCode} method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined
     * by class {@code Object} does return distinct integers for
     * distinct objects. (The hashCode may or may not be implemented
     * as some function of an object's memory address at some point
     * in time.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public
    int hashCode () {
        return super.hashCode();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public
    boolean equals ( Object obj ) {
        return super.equals(obj);
    }

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * will be true, and that the expression:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * will be {@code true}, this is not an absolute requirement.
     * <p>
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}.  If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     * <p>
     * By convention, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by {@code super.clone} before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by {@code super.clone}
     * need to be modified.
     * <p>
     * The method {@code clone} for class {@code Object} performs a
     * specific cloning operation. First, if the class of this object does
     * not implement the interface {@code Cloneable}, then a
     * {@code CloneNotSupportedException} is thrown. Note that all arrays
     * are considered to implement the interface {@code Cloneable} and that
     * the return type of the {@code clone} method of an array type {@code T[]}
     * is {@code T[]} where T is any reference or primitive type.
     * Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of
     * the corresponding fields of this object, as if by assignment; the
     * contents of the fields are not themselves cloned. Thus, this method
     * performs a "shallow copy" of this object, not a "deep copy" operation.
     * <p>
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if the object's class does not
     *                                    support the {@code Cloneable} interface. Subclasses
     *                                    that override the {@code clone} method can also
     *                                    throw this exception to indicate that an instance cannot
     *                                    be cloned.
     * @see Cloneable
     */
    @Override
    protected
    Object clone () throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public
    String toString () {
        return super.toString();
    }

    /**
     * Called by the garbage collector on an object when garbage collection
     * determines that there are no more references to the object.
     * A subclass overrides the {@code finalize} method to dispose of
     * system resources or to perform other cleanup.
     * <p>
     * The general contract of {@code finalize} is that it is invoked
     * if and when the Java&trade; virtual
     * machine has determined that there is no longer any
     * means by which this object can be accessed by any thread that has
     * not yet died, except as a result of an action taken by the
     * finalization of some other object or class which is ready to be
     * finalized. The {@code finalize} method may take any action, including
     * making this object available again to other threads; the usual purpose
     * of {@code finalize}, however, is to perform cleanup actions before
     * the object is irrevocably discarded. For example, the finalize method
     * for an object that represents an input/output connection might perform
     * explicit I/O transactions to break the connection before the object is
     * permanently discarded.
     * <p>
     * The {@code finalize} method of class {@code Object} performs no
     * special action; it simply returns normally. Subclasses of
     * {@code Object} may override this definition.
     * <p>
     * The Java programming language does not guarantee which thread will
     * invoke the {@code finalize} method for any given object. It is
     * guaranteed, however, that the thread that invokes finalize will not
     * be holding any user-visible synchronization locks when finalize is
     * invoked. If an uncaught exception is thrown by the finalize method,
     * the exception is ignored and finalization of that object terminates.
     * <p>
     * After the {@code finalize} method has been invoked for an object, no
     * further action is taken until the Java virtual machine has again
     * determined that there is no longer any means by which this object can
     * be accessed by any thread that has not yet died, including possible
     * actions by other objects or classes which are ready to be finalized,
     * at which point the object may be discarded.
     * <p>
     * The {@code finalize} method is never invoked more than once by a Java
     * virtual machine for any given object.
     * <p>
     * Any exception thrown by the {@code finalize} method causes
     * the finalization of this object to be halted, but is otherwise
     * ignored.
     *
     * @throws Throwable the {@code Exception} raised by this method
     * @apiNote Classes that embed non-heap resources have many options
     * for cleanup of those resources. The class must ensure that the
     * lifetime of each instance is longer than that of any resource it embeds.
     * {@link Reference#reachabilityFence} can be used to ensure that
     * objects remain reachable while resources embedded in the object are in use.
     * <p>
     * A subclass should avoid overriding the {@code finalize} method
     * unless the subclass embeds non-heap resources that must be cleaned up
     * before the instance is collected.
     * Finalizer invocations are not automatically chained, unlike constructors.
     * If a subclass overrides {@code finalize} it must invoke the superclass
     * finalizer explicitly.
     * To guard against exceptions prematurely terminating the finalize chain,
     * the subclass should use a {@code try-finally} block to ensure
     * {@code super.finalize()} is always invoked. For example,
     * <pre>{@code      @Override
     *     protected void finalize() throws Throwable {
     *         try {
     *             ... // cleanup subclass state
     *         } finally {
     *             super.finalize();
     *         }
     *     }
     * }</pre>
     * @jls 12.6 Finalization of Class Instances
     * @see WeakReference
     * @see PhantomReference
     * @deprecated The finalization mechanism is inherently problematic.
     * Finalization can lead to performance issues, deadlocks, and hangs.
     * Errors in finalizers can lead to resource leaks; there is no way to cancel
     * finalization if it is no longer necessary; and no ordering is specified
     * among calls to {@code finalize} methods of different objects.
     * Furthermore, there are no guarantees regarding the timing of finalization.
     * The {@code finalize} method might be called on a finalizable object
     * only after an indefinite delay, if at all.
     * <p>
     * Classes whose instances hold non-heap resources should provide a method
     * to enable explicit release of those resources, and they should also
     * implement {@link AutoCloseable} if appropriate.
     * The {@link Cleaner} and {@link PhantomReference}
     * provide more flexible and efficient ways to release resources when an object
     * becomes unreachable.
     */
    @Override
    protected
    void finalize () throws Throwable {
        super.finalize();
    }

    /**
     * @return
     */
    @Override
    public
    IPipeline <IImage <A>, IImage <A>> getLinkedObject () {
        return null;
    }

    /**
     * @param link
     */
    @Override
    public
    void setNext ( ISingleLinked <IPipeline <IImage <A>, IImage <A>>> link ) {

    }

    @Override
    public
    ISingleLinked <IPipeline <IImage <A>, IImage <A>>> getNext () {
        return null;
    }

    @Override
    public
    IImage <A> getInput () {
        return null;
    }

    @Override
    public
    IImage <A> getOutput () {
        return null;
    }
}
