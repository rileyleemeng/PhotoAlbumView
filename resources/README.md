# Shapes Photo Album

### Overview

        This project builds upon the previous Shapes Photo Album application, 
        introducing significant changes to the architecture for better modularity, flexibility, and feature enhancement. 
        The system allows users to create and manipulate shapes, take snapshots of their current states, 
        and view these snapshots in graphical or web formats.

### Changes from previous and why

        1. The project is now split into three distinct modules:
            Controller: Manages user inputs and interactions.
            Model: Handles data logic and storage.
            Views: Renders outputs in graphical or web formats.
        Reason: Improves code clarity, separation of concerns, and maintainability.

        2. The previous SnapshotRender class was removed. Snapshot rendering responsibilities have been distributed across Snapshot.java and view classes.
        Reason: Simplifies architecture by delegating rendering to the respective output components.
        
        3. Introduced new interfaces (IPhotoalbum, ISnapshot) in model module to standardize contracts for models and snapshots.
        Reason: Facilitates future extensions and improves consistency.

        4. Change the Snapshot ArrayLists to HashMap, because it can stores ID and snapshots in pair.

## Models Module

### Structure

- Interface: IShape, ISnapshot, IPhotoAlbum
- concrete class: PhotoAlbumModel
    - Shape: 
      - concrete class: Color, Oval, Rectangle, ShapeType
      - abstract class: Shape
    - snapshot:
        - concrete class: Snapshot

### Features

- The Model could create Oval or Rectangle on the canvas.
- The Model could save all shapes and modify them.
  - The Model could move.
  - The Model could change color.
- The Model could take a Snapshot of the canvas at any time. Each Snapshot contains the basic information of itself and the information of all shapes on canvas at that time.
- The Model could save as many Snapshots as possible.
- The Model could search for a specific Snapshot by its ID.


## Controller

### Structure

- Interface: IPhotoalbumController
- concrete class: PhotoalbumController, GraphicalViewController
- I/O: FileReader

### Features

- The Controller could tell Model to make different operations according to user's input.
- The Controller could get different outputs from the View according user's input.
- The Controller could throw exceptions when trying to make an illegal operation on model.
- The Controller could read formatted input source.

## View

### Graphical View - Swing

- Interface: IView
- concrete class: GraphicalView, GraphicalViewFrame, GraphicalViewPanel

#### Features

- The Graphical View provides 4 buttons of operations:
  - Prev: Display the previous snapshot and make a warning when meeting the first one.
  - Select: Print out a InputMessageBox for selecting a snapshot from the list of snapshots' IDs to display.
  - Next: Display the next snapshot and make a warning when meeting the last one.
  - Quit: Quit the Shapes Photos Album Application.
- The Graphical View set the size of displaying area. (Default: 1000 x 1000)
- The Graphical View display the detailed information of a specific Snapshot on the top:
  - ID: The ID (CreatedTimestamp) of the snapshot.
  - Description: The description of the snapshot. Invisible when there is no description.

### Web View - HTML & SVG

#### Structure

- concrete class: WebView

#### Features

- The Web View outputs a file in HTML format.
- The Web View displays all snapshots in a list.
- The Web View draws each snapshots by using SVG.
- The Web View could display the detailed information of a specific Snapshot on the top:
  - ID: The ID (CreatedTimestamp) of the snapshot.
  - Description: The description of the snapshot. Invisible when there is no description

## Main Function

- The Entry function of running the whole program.

### Arguments

- -in: $inputfilename$: Required
- -out: $outputfilename$: Required for Web View
- -v/-view: $viewtype$
  - graphical: Use the Swing graphical view.
  - web: Use the html & svg view
- -x/-y: Set the xMax and yMax value of the size of ISnapshot displaying area. Optional for Graphical View