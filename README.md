# Image Processing 

Packages:

Model:
- Interface ImageProcessingModel
- Class: Model implements ImageProcessingModel

View:
- Interface ImageProcessingView
- Class: View implements ImageProcessingView

Controller:
- Interface ImageProcessingController
- Class: Controller implements ImageProcessingController
- commands: contains the classes for the supported commands following the command design pattern.
Includes interface ImageProcessingCommands containg a "go" method which all command classes implement.

Util:
- Interface Image
- Class ImageImpl implements Image
- ImageProcessingProgram containing the main method
- ImageUtil: utility class utilized for image reading
- Pixel: represents a set of RGB values

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