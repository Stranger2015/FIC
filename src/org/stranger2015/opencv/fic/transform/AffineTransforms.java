package org.stranger2015.opencv.fic.transform;

import org.opencv.core.Range;
import org.stranger2015.opencv.fic.core.IImage;

import java.util.ArrayList;

/**
 *
 */
public
class AffineTransforms extends ArrayList <ImageTransform> {

    /**
     * @param x
     * @param axis
     */
    public
    IImage flipAxis ( IImage x, int axis ) {
        return x;
    }
//    x = np.asarray(x).swapaxes(axis, 0)
//    x = x[::-1, ...]
//    x = x.swapAxes(0, axis)
//    return x

    /**
     * Performs a random rotation of an image tensor.
     *
     * @param image              Input tensor. Must be 3D.
     * @param rg                 Rotation range, in degrees.
     * @param rowAxis            Index of axis for rows in the input tensor.
     * @param colAxis            Index of axis for columns in the input tensor.
     * @param channelAxis        Index of axis for channels in the input tensor.
     * @param fillMode           Points outside the boundaries of the input
     *                           are filled according to the given mode
     *                           (one of `{'constant', 'nearest', 'reflect', 'wrap'}`).
     * @param cval               Value used for points outside the boundaries
     *                           of the input if `mode='constant'`.
     * @param interpolationOrder int, order of spline interpolation.
     *                           see `ndimage.interpolation.affine_transform`
     * @return Rotated image tensor.
     */
    public
    IImage randomTransform/*Rotation*/ ( IImage image,
                                    Range rg,
                                    int rowAxis/*=1*/,
                                    int colAxis/*=2*/,
                                    int channelAxis/*=0*/,
                                    EFillMode fillMode,
                                    int cval,
                                    int interpolationOrder ) {

        return null;
    }

//    int theta = random.nextDouble(-rg, rg);

    /**
     * @param x
     * @param wrg
     * @param hrg
     * @param rowAxis
     * @param colAxis
     * @param channelAxis
     * @param fillMode
     * @param cval
     * @param interpolationOrder
     * @return Shifted image tensor.
     */
    //    x = applyAffineTransform(x, theta=theta, channel_axis=channel_axis,
//                               fill_mode=fill_mode, cval=cval,
//                               order=interpolation_order)
//    return x
//
//    public
//    int randomShift ( int x,
//                      int wrg,
//                      int hrg,
//                      int rowAxis/*=1*/,
//                      int colAxis/*=2*/,
//                      int channelAxis/*=0*/,
//                      int fillMode/*='nearest'*/,
//                      int cval/*=0.*/,
//                      int interpolationOrder/*=1*/ ){
//
//        return 0;//fixme
//    }

//    # Arguments
//        x: Input tensor. Must be 3D.
//        wrg: Width shift range, as a float fraction of the width.
//        hrg: Height shift range, as a float fraction of the height.
//        row_axis: Index of axis for rows in the input tensor.
//        col_axis: Index of axis for columns in the input tensor.
//        channel_axis: Index of axis for channels in the input tensor.
//        fill_mode: Points outside the boundaries of the input
//            are filled according to the given mode
//            (one of `{'constant', 'nearest', 'reflect', 'wrap'}`).
//        cval: Value used for points outside the boundaries
//            of the input if `mode='constant'`.
//        interpolation_order: int, order of spline interpolation.
//            see `ndimage.interpolation.affine_transform`
//    # Returns
//
//    """
//    h, w = x.shape[row_axis], x.shape[col_axis]
//    tx = np.random.uniform(-hrg, hrg) * h
//    ty = np.random.uniform(-wrg, wrg) * w
//    x = applyAffineTransform(x, tx=tx, ty=ty, channel_axis=channel_axis,
//                               fill_mode=fill_mode, cval=cval,
//                               order=interpolation_order)
//    return x
//
//
//def random_shear(x, intensity, row_axis=1, col_axis=2, channel_axis=0,
//                 fill_mode='nearest', cval=0., interpolation_order=1):
//    """Performs a random spatial shear of a Numpy image tensor.
//    # Arguments
//        x: Input tensor. Must be 3D.
//        intensity: Transformation intensity in degrees.
//        row_axis: Index of axis for rows in the input tensor.
//        col_axis: Index of axis for columns in the input tensor.
//        channel_axis: Index of axis for channels in the input tensor.
//        fill_mode: Points outside the boundaries of the input
//            are filled according to the given mode
//            (one of `{'constant', 'nearest', 'reflect', 'wrap'}`).
//        cval: Value used for points outside the boundaries
//            of the input if `mode='constant'`.
//        interpolation_order: int, order of spline interpolation.
//            see `ndimage.interpolation.affine_transform`
//    # Returns
//        Sheared Numpy image tensor.
//    """
//    shear = np.random.uniform(-intensity, intensity)
//    x = apply_affine_transform(x, shear=shear, channel_axis=channel_axis,
//                               fill_mode=fill_mode, cval=cval,
//                               order=interpolation_order)
//    return x
//
//
//def random_zoom(x, zoom_range, row_axis=1, col_axis=2, channel_axis=0,
//                fill_mode='nearest', cval=0., interpolation_order=1):
//    """Performs a random spatial zoom of a Numpy image tensor.
//    # Arguments
//        x: Input tensor. Must be 3D.
//        zoom_range: Tuple of floats; zoom range for width and height.
//        row_axis: Index of axis for rows in the input tensor.
//        col_axis: Index of axis for columns in the input tensor.
//        channel_axis: Index of axis for channels in the input tensor.
//        fill_mode: Points outside the boundaries of the input
//            are filled according to the given mode
//            (one of `{'constant', 'nearest', 'reflect', 'wrap'}`).
//        cval: Value used for points outside the boundaries
//            of the input if `mode='constant'`.
//        interpolation_order: int, order of spline interpolation.
//            see `ndimage.interpolation.affine_transform`
//    # Returns
//        Zoomed Numpy image tensor.
//    # Raises
//        ValueError: if `zoom_range` isn't a tuple.
//    """
//    if len(zoom_range) != 2:
//        raise ValueError('`zoom_range` should be a tuple or list of two'
//                         ' floats. Received: %s' % (zoom_range,))
//
//    if zoom_range[0] == 1 and zoom_range[1] == 1:
//        zx, zy = 1, 1
//    else:
//        zx, zy = np.random.uniform(zoom_range[0], zoom_range[1], 2)
//    x = apply_affine_transform(x, zx=zx, zy=zy, channel_axis=channel_axis,
//                               fill_mode=fill_mode, cval=cval,
//                               order=interpolation_order)
//    return x
//
//
//def apply_channel_shift(x, intensity, channel_axis=0):
//    """Performs a channel shift.
//    # Arguments
//        x: Input tensor. Must be 3D.
//        intensity: Transformation intensity.
//        channel_axis: Index of axis for channels in the input tensor.
//    # Returns
//        Numpy image tensor.
//    """
//    x = np.rollaxis(x, channel_axis, 0)
//    min_x, max_x = np.min(x), np.max(x)
//    channel_images = [
//        np.clip(x_channel + intensity,
//                min_x,
//                max_x)
//        for x_channel in x]
//    x = np.stack(channel_images, axis=0)
//    x = np.rollaxis(x, 0, channel_axis + 1)
//    return x
//
//
//def random_channel_shift(x, intensity_range, channel_axis=0):
//    """Performs a random channel shift.
//    # Arguments
//        x: Input tensor. Must be 3D.
//        intensity_range: Transformation intensity.
//        channel_axis: Index of axis for channels in the input tensor.
//    # Returns
//        Numpy image tensor.
//    """
//    intensity = np.random.uniform(-intensity_range, intensity_range)
//    return apply_channel_shift(x, intensity, channel_axis=channel_axis)
//
//
//def apply_brightness_shift(x, brightness):
//    """Performs a brightness shift.
//    # Arguments
//        x: Input tensor. Must be 3D.
//        brightness: Float. The new brightness value.
//        channel_axis: Index of axis for channels in the input tensor.
//    # Returns
//        Numpy image tensor.
//    # Raises
//        ValueError if `brightness_range` isn't a tuple.
//    """
//    if ImageEnhance is None:
//        raise ImportError('Using brightness shifts requires PIL. '
//                          'Install PIL or Pillow.')
//    x = array_to_img(x)
//    x = imgenhancer_Brightness = ImageEnhance.Brightness(x)
//    x = imgenhancer_Brightness.enhance(brightness)
//    x = img_to_array(x)
//    return x
//
//
//def random_brightness(x, brightness_range):
//    """Performs a random brightness shift.
//    # Arguments
//        x: Input tensor. Must be 3D.
//        brightness_range: Tuple of floats; brightness range.
//        channel_axis: Index of axis for channels in the input tensor.
//    # Returns
//        Numpy image tensor.
//    # Raises
//        ValueError if `brightness_range` isn't a tuple.
//    """
//    if len(brightness_range) != 2:
//        raise ValueError(
//            '`brightness_range should be tuple or list of two floats. '
//            'Received: %s' % (brightness_range,))
//
//    u = np.random.uniform(brightness_range[0], brightness_range[1])
//    return apply_brightness_shift(x, u)
//
//
//def transform_matrix_offset_center(matrix, x, y):
//    o_x = float(x) / 2 + 0.5
//    o_y = float(y) / 2 + 0.5
//    offset_matrix = np.array([[1, 0, o_x], [0, 1, o_y], [0, 0, 1]])
//    reset_matrix = np.array([[1, 0, -o_x], [0, 1, -o_y], [0, 0, 1]])
//    transform_matrix = np.dot(np.dot(offset_matrix, matrix), reset_matrix)
//    return transform_matrix
//
//
//def apply_affine_transform(x, theta=0, tx=0, ty=0, shear=0, zx=1, zy=1,
//                           row_axis=0, col_axis=1, channel_axis=2,
//                           fill_mode='nearest', cval=0., order=1):
//    """Applies an affine transformation specified by the parameters given.
//    # Arguments
//        x: 2D numpy array, single image.
//        theta: Rotation angle in degrees.
//        tx: Width shift.
//        ty: Heigh shift.
//        shear: Shear angle in degrees.
//        zx: Zoom in x direction.
//        zy: Zoom in y direction
//        row_axis: Index of axis for rows in the input image.
//        col_axis: Index of axis for columns in the input image.
//        channel_axis: Index of axis for channels in the input image.
//        fill_mode: Points outside the boundaries of the input
//            are filled according to the given mode
//            (one of `{'constant', 'nearest', 'reflect', 'wrap'}`).
//        cval: Value used for points outside the boundaries
//            of the input if `mode='constant'`.
//        order: int, order of interpolation
//    # Returns
//        The transformed version of the input.
//    """
//    if scipy is None:
//        raise ImportError('Image transformations require SciPy. '
//                          'Install SciPy.')
//    transform_matrix = None
//    if theta != 0:
//        theta = np.deg2rad(theta)
//        rotation_matrix = np.array([[np.cos(theta), -np.sin(theta), 0],
//                                    [np.sin(theta), np.cos(theta), 0],
//                                    [0, 0, 1]])
//        transform_matrix = rotation_matrix
//
//    if tx != 0 or ty != 0:
//        shift_matrix = np.array([[1, 0, tx],
//                                 [0, 1, ty],
//                                 [0, 0, 1]])
//        if transform_matrix is None:
//            transform_matrix = shift_matrix
//        else:
//            transform_matrix = np.dot(transform_matrix, shift_matrix)
//
//    if shear != 0:
//        shear = np.deg2rad(shear)
//        shear_matrix = np.array([[1, -np.sin(shear), 0],
//                                 [0, np.cos(shear), 0],
//                                 [0, 0, 1]])
//        if transform_matrix is None:
//            transform_matrix = shear_matrix
//        else:
//            transform_matrix = np.dot(transform_matrix, shear_matrix)
//
//    if zx != 1 or zy != 1:
//        zoom_matrix = np.array([[zx, 0, 0],
//                                [0, zy, 0],
//                                [0, 0, 1]])
//        if transform_matrix is None:78
//        else:
//            transform_matrix = np.dot(transform_matrix, zoom_matrix)
//
//    if transform_matrix is not None:
//        h, w = x.shape[row_axis], x.shape[col_axis]
//        transform_matrix = transform_matrix_offset_center(
//            transform_matrix, h, w)
//        x = np.rollaxis(x, channel_axis, 0)
//        final_affine_matrix = transform_matrix[:2, :2]
//        final_offset = transform_matrix[:2, 2]
//
//        channel_images = [ndimage.interpolation.affine_transform(
//            x_channel,
//            final_affine_matrix,
//            final_offset,
//            order=order,
//            mode=fill_mode,
//            cval=cval) for x_channel in x]
//        x = np.stack(channel_images, axis=0)
//        x = np.rollaxis(x, 0, channel_axis + 1)
//    return x
}
