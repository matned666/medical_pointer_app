# Medical roentgen pointer

Simple test application - create points on a roentgen picture.<br>
- Possibility to add points - all informations are on a list<br>
- Possibility to change color of point<br>
- Previously added point's color affects next points<br>
- Id is visible close to the point, so it is possible to recognize point on the list.
- Possibility to move points  by drag and drop... <br>
- Life refreshing both on the list and all 4 canvas. <br>
- Possibility to save list of points in json and to load them again.<br>
- User can clean whole screen or delete  chosen points<br>
- Picture can be easily taken from disk, not affecting created points.<br>
- Visible memory usage information , number of points and actual refresh rate<br>


----------------------------

# JavaFX

JavaFX framework

Some class names may be taken from MVC model <br>
but there isn't much from it since there is only one view.

--------------------------------------
# Architecture

IWidget implementation goes for canvas and all javafx elements, excluding FXML imports.


--------------------------------------
# Requirements

JavaFX

Java 11

VM settings: <br>
`--module-path /<pathToJavaFX>/openjfx-11.0.2_linux-x64_bin-sdk/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml`

--------------------------
# Usage
RUN AND BE HAPPY!


![Screenshot](https://raw.githubusercontent.com/matned666/medical_pointer_app/Matned/screenshot.png)

------------------------------
# Tests

A few tests for Service and Point

--------------------------

# Made by:

Mateusz N.