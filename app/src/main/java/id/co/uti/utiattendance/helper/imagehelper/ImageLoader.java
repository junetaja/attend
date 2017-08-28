package id.co.uti.utiattendance.helper.imagehelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import id.co.uti.utiattendance.R;


/**
 * Created by nandajulianda on 12/1/16.
 */

public class ImageLoader {

    public enum ImageEngineType {
        PICASSO,
        GLIDE
    }

    private ImageEngineType engineType;
    private Context context;
    private String uri;
    private ImageView imageView;
    private int placeholderImg = 0;
    private int errorImgSrc = 0;

    private DiskCacheStrategy diskCacheStrategy;

    private ImageLoader(Context context, @Nullable ImageEngineType engineType) {
        this.context = context;

        if(engineType != null) {
            this.engineType = engineType;
        } else {
            this.engineType = ImageEngineType.GLIDE;
        }
        this.diskCacheStrategy = DiskCacheStrategy.ALL;
    }

    public static ImageLoader create(@NonNull Context context) {
        return new ImageLoader(context, null);
    }

    public static ImageLoader create(@NonNull Context context, @NonNull ImageEngineType engineType) {
        return new ImageLoader(context, engineType);
    }

    public ImageLoader load(@NonNull String uri) {
        this.uri = uri;
        return this;
    }

    public ImageLoader into(@NonNull ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public ImageLoader placeholder(int placeholderImg) {
        this.placeholderImg = placeholderImg;
        return this;
    }

    public ImageLoader onError(int errorImgSrc) {
        this.errorImgSrc = errorImgSrc;
        return this;
    }

    /**
     * Glide cache strategy
     * just for glide bro
     */
    public ImageLoader diskCacheStrategy(@NonNull DiskCacheStrategy diskCacheStrategy) {
        if(this.engineType == ImageEngineType.GLIDE) {
            this.diskCacheStrategy = diskCacheStrategy;
        }
        return this;
    }

    public void draw() {
        switch (this.engineType) {
            case PICASSO:
                break;
            case GLIDE:
                drawWithGlide();
                break;
        }
    }

    private int getErrorImg() {
        return errorImgSrc > 0 ? errorImgSrc : R.drawable.picture_not_found;
    }

    private void drawWithGlide() {
        if(placeholderImg > 0) {
            Glide.with(context)
                    .load(uri)
                    .placeholder(placeholderImg)
                    .error(getErrorImg())
                    .diskCacheStrategy(this.diskCacheStrategy)
                    .listener(new GlideLoggingListener<>())
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(uri)
                    .error(getErrorImg())
                    .diskCacheStrategy(this.diskCacheStrategy)
                    .listener(new GlideLoggingListener<>())
                    .into(imageView);
        }
    }

}
