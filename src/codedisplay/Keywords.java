package codedisplay;

/** @author Xavier */
public class Keywords {

  public static boolean isDatatype(String word) {
    // Datatype #E2661A = 226, 102, 26
    String[] datatype = {
      "boolean", "byte", "char",
      "double", "float", "int",
      "long", "short", "Array",
      "ArrayList", "HashMap", "String",
      "PShape", "BufferedReader",
      "PrintWriter", "PImage",
      "PGraphics", "PFont",
      "PVector", "XML"
    };
    for (String kw : datatype) {
      if (word.equals(kw)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isConstant(String word) {
    // Constantes #718A62 = 113, 138, 98
    String[] constants = {
      "HALF_PI", "PI", "QUARTER_PI",
      "TWO_PI", "LEFT", "UP", "ARGB",
      "RIGHT", "DOWN", "CODED", "RGB",
      "P2D", "P3D", "OPEN", "GRAY",
      "CLOSE", "ARC", "JAVA2D", "TAU"
    };
    for (String kw : constants) {
      if (word.equals(kw)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isControl(String word) {
    // Controls #669900 = 102, 153, 0
    String[] controls = {
      "catch", "do", "else", "for",
      "if", "switch", "synchronized",
      "try", "while"
    };
    for (String kw : controls) {
      if (word.equals(kw)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isJava(String word) {
    // Java keywords #33997E = 51, 153, 126
    String[] java = {
      "abstract", "assert", "break", "case",
      "class", "continue", "default", "enum",
      "extends", "false", "final", "finally",
      "implements", "import", "instanceof",
      "interface", "native", "new", "null",
      "package", "private", "protected", "public",
      "return", "static", "strictfp", "super",
      "this", "throw", "throws", "transient",
      "true", "void", "volatile"
    };
    for (String kw : java) {
      if (word.equals(kw)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isGlobal(String word) {
    // Processing variables globales #d94a7a = 217, 74, 122
    String[] globals = {
      "focused", "frameCount", "frameRate", "height",
      "width", "mouseButton", "mousePressed", "mouseX",
      "mouseY", "pmouseX", "pmouseY", "key", "keyCode",
      "keyPressed", "pixels"
    };
    for (String kw : globals) {
      if (word.equals(kw)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isFunction(String word) {
    // Functions #006699 = 0, 102, 153
    String[] functions = {
      "draw", "setup", "mouseClicked", "mouseDragged",
      "mouseMoved", "mousePressed", "mouseReleased",
      "keyPressed", "keyReleased", "keyTyped", "delay",
      "exit", "loop", "noLoop", "popStyle", "pushStyle",
      "redraw", "size", "cursor", "frameRate", "noCursor",
      "binary", "boolean", "byte", "char", "float",
      "hex", "int", "str", "unbinary", "unhex", "join",
      "match", "matchAll", "nf", "nfc", "nfp", "nfs",
      "split", "splitTokens", "trim", "append", "arrayCopy",
      "concat", "expand", "reverse", "shorten", "sort",
      "splice", "subset", "arc", "ellipse", "line",
      "point", "quad", "rect", "triangle", "bezier",
      "bezierDetail", "bezierPoint", "bezierTangent", "curve",
      "curveDetail", "curvePoint", "curveTangent", "curveTightness",
      "box", "sphere", "sphereDetail", "ellipseMode", "noSmooth",
      "rectMode", "smooth", "strokeCap", "strokeJoin", "strokeWeight",
      "beginShape", "bezierVertex", "curveVertex", "endShape",
      "texture", "textureMode", "vertex", "loadShape", "shape",
      "shapeMode", "createInput", "createReader", "loadBytes",
      "loadStrings", "selectFolder", "selectInput", "day",
      "hour", "millis", "minute", "month", "second", "year",
      "print", "println", "save", "saveFrame", "beginRaw", "beginRecord",
      "createOutput", "createWriter", "endRaw", "endRecord", "saveBytes",
      "saveStream", "saveStrings", "selectOutput", "applyMatrix",
      "popMatrix", "printMatrix", "pushMatrix", "resetMatrix",
      "rotate", "rotateX", "rotateY", "rotateZ", "scale",
      "shearX", "shearY", "translate", "ambientLight", "directionalLight",
      "lightFalloff", "lightSpecular", "lights", "noLights",
      "normal", "pointLight", "spotLight", "beginCamera",
      "camera", "endCamera", "frustum", "ortho", "perspective",
      "printCamera", "printProjection", "modelX", "modelY",
      "modelZ", "screenX", "screenY", "screenZ", "ambient",
      "emissive", "shininess", "specular", "background",
      "colorMode", "fill", "noFill", "noStroke", "stroke",
      "alpha", "blendColor", "blue", "brightness",
      "color", "green", "hue", "lerpColor", "red",
      "saturation", "createImage", "image", "imageMode",
      "loadImage", "noTint", "requestImage", "tint",
      "blend", "copy", "filter", "get", "loadPixels",
      "set", "updatePixels", "createGraphics", "hint", "createFont",
      "loadFont", "text", "textFont", "textAlign",
      "textLeading", "textMode", "textSize", "textWidth",
      "textAscent", "textDescent", "abs", "ceil", "constrain",
      "dist", "exp", "floor", "lerp", "log", "mag",
      "map", "max", "min", "norm", "pow", "round", "sq",
      "sqrt", "acos", "asin", "atan", "atan2", "cos",
      "degrees", "radians", "sin", "tan", "noise", "noiseDetail",
      "noiseSeed", "random", "randomSeed"
    };
    for (String kw : functions) {
      if (word.equals(kw)) {
        return true;
      }
    }
    return false;
  }

}
