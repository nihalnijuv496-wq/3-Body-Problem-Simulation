package com.nihal.nbodyproblem.Util;

import javafx.scene.paint.Color;

import java.util.Arrays;

public final class ColorGenerator {

    public static Color[] bodyColors = ColorGenerator.getNColors(Constants.N);
    public static Color[] trailColors = ColorGenerator.getNTrailColors(bodyColors);

    public static Color getBodyColor(int i)
    {
        if(i >= bodyColors.length) bodyColors = getNColors(Constants.N);
        return  bodyColors[i];
    }
    public static Color getTrailColor(int i)
    {
        if(i >= trailColors.length) trailColors = getNTrailColors(bodyColors);
        return  trailColors[i];
    }



    static Color[] getNColors(int n)
    {
        final Color[] colors = new Color[n];
        final double goldenRatioConjugate = 0.618033988749895;
        double hue = Math.random();

        for (int i = 0; i < n; i++) {
            hue += goldenRatioConjugate;
            hue %= 1;

            colors[i] = hslToRgbColor(hue, 0.65, 0.50);
        }
        return colors;
    }

    static Color[] getNTrailColors(Color[] bodyColors)
    {
        return Arrays.stream(bodyColors).map(color -> new Color(
                        Math.min(1, color.getRed() + 0.35),
                        Math.min(1, color.getGreen() + 0.35),
                        Math.min(1, color.getBlue() + 0.35), 1.0)).toArray(Color[]::new);
    }

    private static Color hslToRgbColor(double h, double s, double l) {
        double q = l < 0.5 ? l * (1.0 + s) : l + s - l * s;
        double p = 2.0 * l - q;

        double r = hueToRgb(p, q, h + 1.0 / 3.0);
        double g = hueToRgb(p, q, h);
        double b = hueToRgb(p, q, h - 1.0 / 3.0);

        return Color.color(r, g, b);
    }

    private static double hueToRgb(double p, double q, double t) {
        if (t < 0.0) t += 1.0;
        if (t > 1.0) t -= 1.0;
        if (t < 1.0 / 6.0) return p + (q - p) * 6.0 * t;
        if (t < 1.0 / 2.0) return q;
        if (t < 2.0 / 3.0) return p + (q - p) * (2.0 / 3.0 - t) * 6.0;
        return p;
    }



}
