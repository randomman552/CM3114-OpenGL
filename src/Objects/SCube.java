package Objects;

public class SCube extends SObject {
    private float size;

    public SCube() {
        this(1);
    }

    public SCube(float size) {
        super();
        this.size = size;
        update();
    }

    @Override
    protected void genData() {
        // How far each vert is from the center of the cube
        float size = this.size / 2;

        // Generate vertices
        vertices = new float[] {
            -size, -size, -size,
             size, -size, -size,
            -size,  size, -size,
             size,  size, -size,
            -size, -size,  size,
             size, -size,  size,
            -size,  size,  size,
             size,  size,  size
        };
        numVertices = vertices.length;

        // Generate normals
        normals = vertices;

        // Generate textures
        textures = new float[numVertices * 3];

        indices = new int[] {
            2, 1, 0, // Back 1
            1, 2, 3, // Back 2

            6, 7, 2, // Top 1
            3, 2, 7, // Top 2

            0, 1, 4, // Bottom 1
            5, 4, 1, // Bottom 2

            4, 5, 6, // Front 1
            7, 6, 5, // Front 2

            5, 1, 7, // Right 1
            3, 7, 1, // Right 2

            0, 4, 2, // Left 1
            6, 2, 4, // Left 2
        };
        numIndices = indices.length;
    }

    public void setSize(float size) {
        this.size = size;
        this.updated = false;
    }

    public float getSize() {
        return this.size;
    }
}
