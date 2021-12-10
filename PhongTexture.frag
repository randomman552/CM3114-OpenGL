#version 330 core

in vec3 N;
in vec3 P;
in vec2 texCoord;

out vec4 color;

uniform vec4 LightPosition;
uniform vec4 AmbientProduct, DiffuseProduct, SpecularProduct;
uniform float Shininess;

// Textures
uniform sampler2D decalTex;
uniform sampler2D normalTex;

mat3 tbn()
{
	vec3 normal = N;
	vec3 tangent = normalize(vec3(texCoord[0], 0, 0));
	vec3 binormal = normalize(vec3(0, texCoord[1], 0));

	mat3 tbn = mat3(tangent, binormal, normal);
	return transpose(tbn);
}

void main()
{
	// Light position in eye coordinates
	vec3 L = normalize(LightPosition.xyz - P);
	vec3 E = normalize(-P);
	vec3 H = normalize(L + E);

	// Ambient lighting
	vec4 ambient = AmbientProduct;

	// Diffuse lighting
	// Get normal from the normal map
	vec3 normal = normalize(texture2D(normalTex, texCoord).rgb * 2.0 - 1.0);
	// TBN is a matrix used to convert the normal into tangent space
	// This means that lighting works with the normal map
	normal *= tbn();
	// Diffuse calculation continues as normal
	float Kd = max(dot(L, normal), 0.0);
	//float Kd = max(dot(L, N), 0.0);
	vec4  diffuse = Kd*DiffuseProduct;

	// Specular lighting
	float Ks = pow( max(dot(N, H), 0.0), Shininess );
	vec4  specular = Ks * SpecularProduct;

	if( dot(L, N) < 0.0 ) {
		specular = vec4(0.0, 0.0, 0.0, 1.0);
	}

	// Calculate color result
	color = ambient + diffuse + specular;

	// Apply texture
	color *= texture2D(decalTex, texCoord);
}