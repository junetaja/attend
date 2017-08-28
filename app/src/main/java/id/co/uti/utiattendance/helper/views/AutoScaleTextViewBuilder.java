package id.co.uti.utiattendance.helper.views;

import android.content.Context;

/**
 * Created by Nanda J.A on 4/29/2015.
 * AutoScaleTextView helper/builder
 */
public class AutoScaleTextViewBuilder {

    private static AutoScaleTextViewBuilder instance;
    private Context context;
    private AutoScaleTextView textView;

    private AutoScaleTextViewBuilder() {}

    public static AutoScaleTextViewBuilder create(Context context) {
        if(instance == null) {
            instance = new AutoScaleTextViewBuilder();
        }
        instance.context = context;
        return instance;
    }

    public AutoScaleTextViewBuilder setTextView(AutoScaleTextView textView) {
        instance.textView = textView;
        return instance;
    }

    public AutoScaleTextViewBuilder setMinSize(int size) {
        textView.setMinTextSize(context.getResources().getDimension(size));
        return instance;
    }

    public AutoScaleTextViewBuilder setMinSize(float size) {
        textView.setMinTextSize(size);
        return instance;
    }

    public AutoScaleTextViewBuilder setMaxSize(int size) {
        textView.setTextSize(context.getResources().getDimension(size));
        return instance;
    }

    public AutoScaleTextViewBuilder setMaxSize(float size) {
        textView.setTextSize(size);
        return instance;
    }

    public AutoScaleTextViewBuilder setText(String text) {
        textView.setText(text);
        return instance;
    }

}
