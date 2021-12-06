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
        // Quite a bit of redundant data here, but simplifying would likely increase complexity
        // How far each vert is from the center of the cube
        float size = this.size / 2;

        // region Generate vertices
        // Use 24 vertices, so we can have proper normals on each face
        vertices = new float[] {
            // Back
            -size, -size, -size,
             size, -size, -size,
            -size,  size, -size,
             size,  size, -size,

            // Top
            -size,  size, -size,
             size,  size, -size,
            -size,  size,  size,
             size,  size,  size,

            // Bottom
            -size,  -size, -size,
             size,  -size, -size,
            -size,  -size,  size,
             size,  -size,  size,

            // Front
            -size, -size, size,
             size, -size, size,
            -size,  size, size,
             size,  size, size,

            // Left
            -size, -size, -size,
            -size,  size, -size,
            -size, -size,  size,
            -size,  size,  size,

            // Right
            size, -size, -size,
            size,  size, -size,
            size, -size,  size,
            size,  size,  size,
        };
        numVertices = vertices.length / 3;
        // endregion

        // region Generate normals
        normals = new float[] {
                // Back
                0, 0, -1,
                0, 0, -1,
                0, 0, -1,
                0, 0, -1,

                // Top
                0, 1, 0,
                0, 1, 0,
                0, 1, 0,
                0, 1, 0,

                // Bottom
                0, -1, 0,
                0, -1, 0,
                0, -1, 0,
                0, -1, 0,

                // Front
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,

                // Left
                -1, 0, 0,
                -1, 0, 0,
                -1, 0, 0,
                -1, 0, 0,

                // Right
                1, 0, 0,
                1, 0, 0,
                1, 0, 0,
                1, 0, 0,
        };
        // endregion

        // region Generate textures
        textures = new float[] {
                // Back
                1, 0,
                0, 0,
                1, 1,
                0, 1,

                // Top
                0, 1,
                1, 1,
                0, 0,
                1, 0,

                // Bottom
                0, 1,
                1, 1,
                0, 0,
                1, 0,

                // Front
                0, 0,
                1, 0,
                0, 1,
                1, 1,

                // Left
                0, 0,
                0, 1,
                1, 0,
                1, 1,

                // Right
                1, 0,
                1, 1,
                0, 0,
                0, 1,
        };

        // endregion

        // region Generate indices
        indices = new int[] {
                2, 1, 0, // Back 1
                1, 2, 3, // Back 2

                6, 5, 4, // Top 1
                5, 6, 7, // Top 2

                8, 9, 10, // Bottom 1
                11, 10, 9, // Bottom 2

                12, 13, 14, // Front 1
                15, 14, 13, // Front 2

                18, 17, 16, // Left 1
                17, 18, 19, // Left 2

                20, 21, 22, // Right 1
                23, 22, 21, // Right 2
        };
        numIndices = indices.length;
        // endregion
    }

    public void setSize(float size) {
        this.size = size;
        this.updated = false;
    }

    public float getSize() {
        return this.size;
    }
}
