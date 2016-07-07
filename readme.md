## Svg2Drawable

Will convert an SVG file to a bunch of DPI appropriate Android *PNG* files.
It does not produce any Vector Drawables (yet :p)


### Usage

	svg2drawable-0.1.jar [-converter <arg>] -icontype <arg> -input <arg> -output <arg> [-overwrite]

	Convert a SVG file to a bunch on Android PNG drawables.

	 -converter <arg>   The converter to use. Must be one of: [batik,
	                    inkscape] (case insensitive). The default value is
	                    'batik'. To use 'inkscape' as a converter, the
	                    inkscape binary must be in your PATH.
	 -icontype <arg>    The icon type to export. Must be one of: [actionbar,
	                    contextual, dialog, launcher, notification, tab,
	                    web_icon] (case insensitive).
	 -input <arg>       The SVG input file. It must exist and be readable.
	 -output <arg>      The output directory. It must exist and be writable.
	 -overwrite         Overwrite existing files. If this is not set and ANY
	                    of the target files exist, the app will abort.

### Example Usage

	svg2drawable-0.1.jar -input ~/tmp/image/image.svg -output ~/project/app/src/main/res/ -icontype launcher -overwrite
	svg2drawable-0.1.jar -input ~/tmp/image/image.svg -output ~/project/app/src/main/res/ -icontype tab -overwrite -converter inkscape

### Notes
- The final image name will be the same as the input SVG with a PNG extension i.e. image.svg will become image.png.
- The following types  [actionbar, contextual, dialog, notification, tab] will produce the full range of dpi folders in the following format "drawable-DPI/icon".
- The following types  [launcher] will produce the full range of dpi folders in the following format "mipmap-DPI/icon".
- The following types [web_icon] will produce a single icon.

### Build Instructions
Linux/Mac: mvn clean package && chmod +x target/svg2drawable-0.1.jar


### Links
* Github: [https://github.com/alt236/Svg2Drawable]()

### Credits
Author: [Alexandros Schillings](https://github.com/alt236).

The code in this project is licensed under the [Apache Software License 2.0](LICENSE-2.0.html).

Copyright (c) 2016 Alexandros Schillings.
