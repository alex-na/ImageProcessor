# Image Processing 

Packages:

Model:
- Interface ImageProcessingModelState
- Interface ImageProcessingModel extends ImageProcessingModelState
- Class: Model implements ImageProcessingModel

View:
- Interface ImageProcessingView
- Class: View implements ImageProcessingView

Controller:
- Interface ImageProcessingController
- Class: Controller implements ImageProcessingController
- commands: contains the classes for the supported commands following the command design pattern.
Includes interface ImageProcessingCommands containing an "apply" method which all command classes implement.

Util:
- Interface ImageState
- Interface Image extends ImageState
- Class ImageImpl implements Image
- Class ImageProcessingProgram containing the main method
- ImageUtil: utility class utilized for image reading
- Interface Kernel
- Class ImageKernel implements Kernel

Tests:
- ImageProcessingModelTest
- ImageProcessingViewTest
- ImageProcessingControllerTest

Supported functionality:

- load \<file path> \<desired name>
- save \<save location> \<image name>
- brighten \<increment> \<image name> \<desired name>
- horizontal-flip \<image name> \<desired name>
- vertical-flip \<image name> \<desired name>
- value-component \<image name> \<desired name>
- intensity-component \<image name> \<desired name>
- luma-component \<image name> \<desired name>
- red-component \<image name> \<desired name>
- green-component \<image name> \<desired name>
- blue-component \<image name> \<desired name>
- blur \<image name> \<desired name>
- sharpen \<image name> \<desired name>
- sepia \<image name> \<desired name>
- greyscale \<image name> \<desired name>


bunny image citation:

Allanwood, G. (2018, February 16). Photo by Gavin Allanwood on unsplash. Beautiful Free Images &amp; Pictures. Retrieved November 10, 2021, from https://unsplash.com/photos/hcxqLJjI99E. 