import java.io.IOException;
import java.nio.*;

import org.lwjgl.system.MemoryStack;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.stb.STBImageResize.*;

/**
 * <p>
 * The player class contains state and behaviours for a player object.
 * The player object can be controled by the keyboard to perform actions such as 
 * movement and slashing. Contains functionality for drawing the player.
 * </p>
 *
 * <h2>ICS 4U0 with Krasteva, V.</h2>
 *
 * @version 1.0
 * @author Ryan Atlas, Samuel Huang and Daniel Morgan
 * @since May 20th, 2022
 * <p>
 * File was created by Daniel Morgan on may 20th, 2022.
 * Daniel Morgan spent 2 hours on may 20th 2022
 * </p>
 */

public class Texture {

    public static Texture load(String path) throws IOException, Exception {
        
        // System.out.println("Loading " + path + " from")
        ByteBuffer imageBuffer;
        try {
            imageBuffer = IOUtil.ioResourceToByteBuffer(path, 1024*8);
        } catch (Exception e) {
            throw new Exception(e);
        }
        
        try (MemoryStack stack = stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);
            
            // Use info to read image metadata without decoding the entire image.
            // We don't need this for this demo, just testing the API.
            if (!stbi_info_from_memory(imageBuffer, w, h, comp)) {
                throw new Exception("Failed to read image information: " + stbi_failure_reason());
            } else {
                System.out.println("OK with reason: " + stbi_failure_reason());
            }
            
            System.out.println("Image width: " + w.get(0));
            System.out.println("Image height: " + h.get(0));
            System.out.println("Image components: " + comp.get(0));
            System.out.println("Image HDR: " + stbi_is_hdr_from_memory(imageBuffer));

            // Decode the image
            ByteBuffer image = stbi_load_from_memory(imageBuffer, w, h, comp, 4);
            if (image == null) {
                throw new Exception("Failed to load image: " + stbi_failure_reason());
            }

            return new Texture(imageBuffer, -1, w.get(0), h.get(0), comp.get(0));
        } catch (Exception e) {
            throw e;
        }
    }

    private int createTexture() {
        int texID = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, texID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        int format;
        if (comp == 3) {
            if ((width & 3) != 0) {
                glPixelStorei(GL_UNPACK_ALIGNMENT, 2 - (width & 1));
            }
            format = GL_RGB;
        } else {
            premultiplyAlpha();

            glEnable(GL_BLEND);
            glBlendFunc(GL_ONE, GL_ONE_MINUS_SRC_ALPHA);

            format = GL_RGBA;
        }

        glTexImage2D(GL_TEXTURE_2D, 0, format, width, height, 0, format, GL_UNSIGNED_BYTE, image);

        ByteBuffer input_pixels = image;
        int        input_w      = width;
        int        input_h      = height;
        int        mipmapLevel  = 0;
        while (1 < input_w || 1 < input_h) {
            int output_w = Math.max(1, input_w >> 1);
            int output_h = Math.max(1, input_h >> 1);

            ByteBuffer output_pixels = memAlloc(output_w * output_h * comp);
            stbir_resize_uint8_generic(
                input_pixels, input_w, input_h, input_w * comp,
                output_pixels, output_w, output_h, output_w * comp,
                comp, comp == 4 ? 3 : STBIR_ALPHA_CHANNEL_NONE, STBIR_FLAG_ALPHA_PREMULTIPLIED,
                STBIR_EDGE_CLAMP,
                STBIR_FILTER_MITCHELL,
                STBIR_COLORSPACE_SRGB
            );

            if (mipmapLevel == 0) {
                stbi_image_free(image);
            } else {
                memFree(input_pixels);
            }

            glTexImage2D(GL_TEXTURE_2D, ++mipmapLevel, format, output_w, output_h, 0, format, GL_UNSIGNED_BYTE, output_pixels);

            input_pixels = output_pixels;
            input_w = output_w;
            input_h = output_h;
        }
        if (mipmapLevel == 0) {
            stbi_image_free(image);
        } else {
            memFree(input_pixels);
        }

        return texID;
    }

    private void premultiplyAlpha() {
        int stride = width * 4;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int i = y * stride + x * 4;
                float alpha = (image.get(i + 3) & 0xFF) / 255.0f;
                image.put(i + 0, (byte)Math.round(((image.get(i + 0) & 0xFF) * alpha)));
                image.put(i + 1, (byte)Math.round(((image.get(i + 1) & 0xFF) * alpha)));
                image.put(i + 2, (byte)Math.round(((image.get(i + 2) & 0xFF) * alpha)));
            }
        }
    }

    private int width;
    private int height;
    private int id;
    private int comp;
    private ByteBuffer image;

    public Texture(String imagePath) throws IOException, Exception {
        Texture temp = load(imagePath);
        width = temp.getWidth();
        height = temp.getHeight();
        id = temp.getId();
    }

    public Texture(ByteBuffer image, int id, int width, int height, int comp) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.comp = comp;
        this.image = image;
        if (this.id == -1) this.id = createTexture();
        System.out.println(this.id);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getId() {
        return id;
    }
    public int getComp() {
        return id;
    }

}