# image_processing

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
