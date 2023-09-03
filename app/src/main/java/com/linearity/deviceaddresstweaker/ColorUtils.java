package com.linearity.deviceaddresstweaker;

import static com.linearity.deviceaddresstweaker.DeviceAddressTweaker.LoggerLog;

import android.graphics.Color;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class ColorUtils {
    private static final int COLOR_RANGE = 255;
    private static final int HEX = 16;
    private static final String RGB = "rgb";
    private static final String RGBA = "rgba";
    private static final String RGBADELIM = "rgba()";
    private static final String RGBDELIM = "rgb()";
    private static final String TAG = "ColorUtils";
    private static final HashMap<String, Integer> sColorNameMap = new HashMap<>();

    static {
        sColorNameMap.put("transparent", 0);
        sColorNameMap.put("none", 0);
        sColorNameMap.put("aliceblue", -984833);
        sColorNameMap.put("antiquewhite", -332841);
        sColorNameMap.put("aqua", -16711681);
        sColorNameMap.put("aquamarine", -8388652);
        sColorNameMap.put("azure", -983041);
        sColorNameMap.put("beige", -657956);
        sColorNameMap.put("bisque", -6972);
        sColorNameMap.put("black", -16777216);
        sColorNameMap.put("blanchedalmond", -5171);
//        sColorNameMap.put(TemplateTag.COLOR_BLUE, -16776961);
        sColorNameMap.put("blueViolet", -7722014);
        sColorNameMap.put("brown", -5952982);
        sColorNameMap.put("burlywood", -2180985);
        sColorNameMap.put("cadetblue", -10510688);
        sColorNameMap.put("chartreuse", -8388864);
        sColorNameMap.put("chocolate", -2987746);
        sColorNameMap.put("coral", -32944);
        sColorNameMap.put("cornflowerblue", -10185235);
        sColorNameMap.put("cornsilk", -1828);
        sColorNameMap.put("crimson", -2354116);
        sColorNameMap.put("cyan", -16711681);
        sColorNameMap.put("darkblue", -16777077);
        sColorNameMap.put("darkcyan", -16741493);
        sColorNameMap.put("darkgoldenrod", -4684277);
        sColorNameMap.put("darkgray", -5658199);
        sColorNameMap.put("darkgreen", -16751616);
        sColorNameMap.put("darkkhaki", -4343957);
        sColorNameMap.put("darkmagenta", -7667573);
        sColorNameMap.put("darkolivegreen", -11179217);
        sColorNameMap.put("darkorange", -29696);
        sColorNameMap.put("darkorchid", -6737204);
        sColorNameMap.put("darkred", -7667712);
        sColorNameMap.put("darksalmon", -1468806);
        sColorNameMap.put("darkseagreen", -7357297);
        sColorNameMap.put("darkslateblue", -12042869);
        sColorNameMap.put("darkslategray", -13676721);
        sColorNameMap.put("darkturquoise", -16724271);
        sColorNameMap.put("darkviolet", -7077677);
        sColorNameMap.put("deeppink", -60269);
        sColorNameMap.put("deepskyblue", -16728065);
        sColorNameMap.put("dimgray", -9868951);
        sColorNameMap.put("dimgrey", -9868951);
        sColorNameMap.put("dodgerblue", -14774017);
        sColorNameMap.put("firebrick", -5103070);
        sColorNameMap.put("floralwhite", -1296);
        sColorNameMap.put("forestgreen", -14513374);
        sColorNameMap.put("fuchsia", -65281);
        sColorNameMap.put("gainsboro", -2302756);
        sColorNameMap.put("ghostwhite", -460545);
        sColorNameMap.put("gold", -10496);
        sColorNameMap.put("goldenrod", -2448096);
        sColorNameMap.put("gray", -7829368);
        sColorNameMap.put("grey", -8355712);
        sColorNameMap.put(TemplateTag.COLOR_GREEN, -16711936);
        sColorNameMap.put("greenyellow", -5374161);
        sColorNameMap.put("honeydew", -983056);
        sColorNameMap.put("hotpink", -38476);
        sColorNameMap.put("indianred", -3318692);
        sColorNameMap.put("indigo", -11861886);
        sColorNameMap.put("ivory", -16);
        sColorNameMap.put("khaki", -989556);
        sColorNameMap.put("lavender", -1644806);
        sColorNameMap.put("lavenderblush", -3851);
        sColorNameMap.put("lawngreen", -8586240);
        sColorNameMap.put("lemonchiffon", -1331);
        sColorNameMap.put("lightblue", -5383962);
        sColorNameMap.put("lightcoral", -1015680);
        sColorNameMap.put("lightcyan", -2031617);
        sColorNameMap.put("lightgoldenrodyellow", -329006);
        sColorNameMap.put("lightgray", -2894893);
        sColorNameMap.put("lightgrey", -2894893);
        sColorNameMap.put("lightgreen", -7278960);
        sColorNameMap.put("lightpink", -18751);
        sColorNameMap.put("lightsalmon", -24454);
        sColorNameMap.put("lightseagreen", -14634326);
        sColorNameMap.put("lightskyblue", -7876870);
        sColorNameMap.put("lightslategray", -8943463);
        sColorNameMap.put("lightslategrey", -8943463);
        sColorNameMap.put("lightsteelblue", -5192482);
        sColorNameMap.put("lightyellow", -32);
        sColorNameMap.put("lime", -16711936);
        sColorNameMap.put("limegreen", -13447886);
        sColorNameMap.put("linen", -331546);
        sColorNameMap.put("magenta", -65281);
        sColorNameMap.put("maroon", -8388608);
        sColorNameMap.put("mediumaquamarine", -10039894);
        sColorNameMap.put("mediumblue", -16777011);
        sColorNameMap.put("mediumorchid", -4565549);
        sColorNameMap.put("mediumpurple", -7114533);
        sColorNameMap.put("mediumseagreen", -12799119);
        sColorNameMap.put("mediumslateblue", -8689426);
        sColorNameMap.put("mediumspringgreen", -16713062);
        sColorNameMap.put("mediumturquoise", -12004916);
        sColorNameMap.put("mediumvioletred", -3730043);
        sColorNameMap.put("midnightblue", -15132304);
        sColorNameMap.put("mintcream", -655366);
        sColorNameMap.put("mistyrose", -6943);
        sColorNameMap.put("moccasin", -6987);
        sColorNameMap.put("navajowhite", -8531);
        sColorNameMap.put("navy", -16777088);
        sColorNameMap.put("oldlace", -133658);
        sColorNameMap.put("olive", -8355840);
        sColorNameMap.put("olivedrab", -9728477);
        sColorNameMap.put("orange", -23296);
        sColorNameMap.put("orangered", -47872);
        sColorNameMap.put("orchid", -2461482);
        sColorNameMap.put("palegoldenrod", -1120086);
        sColorNameMap.put("palegreen", -6751336);
        sColorNameMap.put("paleturquoise", -5247250);
        sColorNameMap.put("palevioletred", -2396013);
        sColorNameMap.put("papayawhip", -4139);
        sColorNameMap.put("peachpuff", -9543);
        sColorNameMap.put("peru", -3308225);
        sColorNameMap.put("pink", -16181);
        sColorNameMap.put("plum", -2252579);
        sColorNameMap.put("powderblue", -5185306);
        sColorNameMap.put("purple", -8388480);
        sColorNameMap.put("rebeccapurple", -10079335);
        sColorNameMap.put(TemplateTag.COLOR_RED, -65536);
        sColorNameMap.put("rosybrown", -4419697);
        sColorNameMap.put("royalblue", -12490271);
        sColorNameMap.put("saddlebrown", -7650029);
        sColorNameMap.put("salmon", -360334);
        sColorNameMap.put("sandybrown", -744352);
        sColorNameMap.put("seagreen", -13726889);
        sColorNameMap.put("seashell", -2578);
        sColorNameMap.put("sienna", -6270419);
        sColorNameMap.put("silver", -4144960);
        sColorNameMap.put("skyblue", -7876885);
        sColorNameMap.put("slateblue", -9807155);
        sColorNameMap.put("slategray", -9404272);
        sColorNameMap.put("slategrey", -9404272);
        sColorNameMap.put("snow", -1286);
        sColorNameMap.put("springgreen", -16711809);
        sColorNameMap.put("steelblue", -12156236);
        sColorNameMap.put("tan", -2968436);
        sColorNameMap.put("teal", -16744320);
        sColorNameMap.put("thistle", -2572328);
        sColorNameMap.put("tomato", -40121);
        sColorNameMap.put("turquoise", -12525360);
        sColorNameMap.put("violet", -1146130);
        sColorNameMap.put("wheat", -663885);
        sColorNameMap.put("white", -1);
        sColorNameMap.put("whitesmoke", -657931);
        sColorNameMap.put("yellow", -256);
        sColorNameMap.put("yellowgreen", -6632142);
    }

    public static boolean isColor(String str) {
        if (sColorNameMap.get(str.toLowerCase(Locale.ROOT)) != null) {
            return true;
        }
        return str.startsWith("#") || str.startsWith(RGB) || str.endsWith(")") || str.startsWith(RGBA) || str.endsWith(")");
    }

    public static int parseColor(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            if (str.startsWith(RGBA) || str.startsWith(RGB)) {
                StringTokenizer stringTokenizer = null;
                if (str.startsWith(RGBA)) {
                    stringTokenizer = new StringTokenizer(str, RGBADELIM);
                } else if (str.startsWith(RGB)) {
                    stringTokenizer = new StringTokenizer(str, RGBDELIM);
                }
                while (stringTokenizer != null && stringTokenizer.hasMoreElements()) {
                    String nextToken = stringTokenizer.nextToken();
                    if (nextToken != null) {
                        String[] split = nextToken.split(",");
                        if (split.length == 4) {
                            return Color.argb((int) (Float.valueOf(split[3].trim()).floatValue() * 255.0f), Integer.valueOf(split[0].trim()).intValue(), Integer.valueOf(split[1].trim()).intValue(), Integer.valueOf(split[2].trim()).intValue());
                        }
                        if (split.length == 3) {
                            return Color.rgb(Integer.valueOf(split[0].trim()).intValue(), Integer.valueOf(split[1].trim()).intValue(), Integer.valueOf(split[2].trim()).intValue());
                        }
                    }
                }
            } else if (str.charAt(0) == '#') {
                long parseLong = Long.parseLong(str.substring(1), 16);
                if (str.length() == 4) {
                    int parseInt = Integer.parseInt(str.substring(1, 4), 16);
                    int i = parseInt & 3840;
                    int i2 = parseInt & 240;
                    int i3 = parseInt & 15;
                    parseLong = Color.rgb((i >> 8) | (i >> 4), i2 | (i2 >> 4), i3 | (i3 << 4));
                } else if (str.length() == 5) {
                    int parseInt2 = Integer.parseInt(str.substring(1, 5), 16);
                    int i4 = parseInt2 & 15;
                    int i5 = parseInt2 & 15;
                    int i6 = parseInt2 & 15;
                    int i7 = parseInt2 & 15;
                    parseLong = Color.argb(i7 | (i7 << 4), i4 | (i4 << 4), i5 | (i5 << 4), i6 | (i6 << 4));
                } else if (str.length() == 7) {
                    parseLong |= -16777216;
                } else if (str.length() == 9) {
                    parseLong = ((parseLong & (-256)) >> 8) | ((255 & parseLong) << 24);
                }
                return (int) parseLong;
            } else {
                Integer num = sColorNameMap.get(str.toLowerCase(Locale.ROOT));
                if (num != null) {
                    return num.intValue();
                }
            }
            return 0;
        } catch (Exception e) {
//            QLog.e(TAG, 1, "parseColor error" + str + e);
            LoggerLog(e);
            return 0;
        }
    }
}
