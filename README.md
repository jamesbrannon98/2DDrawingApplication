This is a 2D drawing application written with Java.

When using the application, the user is able to draw pictures using rectangles, circles, and lines.
The user has the ability to fill in the shape with a color, choose and second color and fill in the shape with a gradient,
and have the border of the shape be dashed lines. Furthermore, the user also has the ability to alter dash length and line width
while being given the option to undo the last drawing or clear the board entirely.

While nearly completed, a bug persists in this project. When drawing a line, the line is only drawn correctly when drawing from the start point to
the bottom right corner of the application. In two other directions, the line disappears entirely, and draws in the opposite direction when moving
towards the upper left hand corner. While I have yet to discover a solution, I am inclined to believe that the bug is a result of line 479.
