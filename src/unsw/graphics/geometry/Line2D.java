/**
 * 
 */
package unsw.graphics.geometry;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL3;

import unsw.graphics.Point2DBuffer;
import unsw.graphics.Shader;

/**
 * A line in 2D space.
 * 
 * This class is immutable.
 * @author Robert Clifton-Everest
 *
 */
public class Line2D {
    private Point2D start, end;

    public Line2D(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;
    }

    public Point2D getStart() {
        return start;
    }

    public Point2D getEnd() {
        return end;
    }
    
    public void draw(GL3 gl) {
        Point2DBuffer buffer = new Point2DBuffer(2);
        buffer.put(0, start);
        buffer.put(1, end);
        
        int[] names = new int[1];
        gl.glGenBuffers(1, names, 0);
        gl.glBindBuffer(GL.GL_ARRAY_BUFFER, names[0]);
        gl.glBufferData(GL.GL_ARRAY_BUFFER, 2*2*Float.BYTES, buffer.getBuffer(), GL.GL_STATIC_DRAW);
        
        gl.glVertexAttribPointer(Shader.POSITION, 2, GL.GL_FLOAT, false, 0, 0);
        gl.glDrawArrays(GL.GL_LINES, 0, 2);
        
        gl.glDeleteBuffers(1, names, 0);
    }
}
