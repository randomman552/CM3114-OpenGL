package Objects;

public class SCone extends SObject {
    private float radius;
    private float height;
    private int slices;

    public SCone() {
        this(1, 2);
    }

    public SCone(float radius, float height) {
        this(radius, height, 20);
    }

    public SCone(int slices) {
        this(1, 2, slices);
    }

    public SCone(float radius, float height, int slices) {
        super();
        this.radius = radius;
        this.height = height;
        this.slices = slices;
        update();
    }

    @Override
    protected void genData() {
        float deltaSlice = (float) (PI*2/slices);

        // Generate arrays for verts, normals, and textures
        numVertices = (slices+1) + 2;
        vertices = new float[numVertices * 3];
        normals = new float[numVertices * 3];
        textures = new float[numVertices * 2];

        // Variable for iterating over vertices arrays
        int k = 0;

        // North-pole point
        normals[k] = 0; normals[k+1] = 0; normals[k+2] = 1;
        vertices[k] = 0; vertices[k+1] = 0; vertices[k+2] = height/2;
        textures[k]= 0.5f; textures[k+1] = 1.0f;
        k++;

        // Only 1 stack, generate verts for main body
        for(int j = 0; j <= slices; j++) {
            normals[3*k] = cos(deltaSlice*j);
            normals[3*k+1] = sin(deltaSlice*j);
            normals[3*k+2] = 0;
            vertices[3*k] = radius*normals[3*k];
            vertices[3*k+1] = radius*normals[3*k+1];
            vertices[3*k+2] = -height/2;
            textures[2*k] = (float) j/slices;
            textures[2*k+1] = 0;
            k++;
        }

        //South pole point
        normals[3*k] = 0; normals[3*k+1] = 0; normals[3*k+2] = -1;
        vertices[3*k] = 0; vertices[3*k+1] = 0; vertices[3*k+2] = -height/2;
        textures[2*k] = 0.5f; textures[2*k+1] = 0.0f;

        // Generate indices for triangular mesh
        numIndices = slices*6;
        indices = new int[numIndices];

        k = 0;
        //North Pole, numElement:slices*3
        for(int j = 1; j <= slices; j++){
            indices[k++] = 0;
            indices[k++] = j;
            indices[k++] = j+1;
        }

        //South Pole, numElement:slices*3
        int temp = numVertices-1;
        for(int j = temp-1; j > temp-slices - 1; j--){
            indices[k++] = temp;
            indices[k++] = j;
            indices[k++] = j-1;
        }
    }


    public void setRadius(float radius) {
        this.radius = radius;
        this.updated = false;
    }

    public float getRadius() {
        return this.radius;
    }


    public void setHeight(float height) {
        this.height = height;
        this.updated = false;
    }

    public float getHeight() {
        return this.height;
    }


    public void setSlices(int slices) {
        this.slices = slices;
        this.updated = false;
    }

    public int getSlices() {
        return this.slices;
    }
}
