package rain.endless.youlife.util;

import android.content.Context;
import android.graphics.Typeface;

public class FontManager {
    public static final String PATH = "fonts/font_awesome.ttf";
    public static final String PATH_V = "fonts/vivaldi_italic.ttf";

    public static Typeface getTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), PATH);
    }

    public static Typeface getTypefaceVivaldi(Context context) {
        return Typeface.createFromAsset(context.getAssets(), PATH_V);
    }
}
