package rain.endless.youlife.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by RAINY on 5/12/2017.
 */

public class FontManager {
    public static final String PATH = "fonts/fontawesome.ttf";

    public static Typeface getTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), PATH);
    }
}
