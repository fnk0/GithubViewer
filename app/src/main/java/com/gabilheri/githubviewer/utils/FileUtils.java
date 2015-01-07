package com.gabilheri.githubviewer.utils;

import android.util.Log;

import com.gabilheri.githubviewer.R;
import com.gabilheri.githubviewer.data.repo.RepoContent;

import org.markdown4j.Markdown4jProcessor;

import java.io.IOException;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 11/26/14.
 */
public class FileUtils {

    public static int getFileDrawable(FileType fileType) {

        switch (fileType) {
            case JAVA:
                return R.drawable.ic_java;
            case C:
                return R.drawable.ic_c;
            case RUBY:
                return R.drawable.ic_ruby;
            case TEXT:
                return R.drawable.ic_txt;
            case PYTHON:
                return R.drawable.ic_python;
            case CPP:
                return R.drawable.ic_cpp;
            case JS:
                return R.drawable.ic_js;
            case CSS:
                return R.drawable.ic_css;
            case DIR:
                return R.drawable.ic_folder;
            default:
                return R.drawable.ic_file_default;
        }
    }

    public static FileType getFileType(String fileName) {
        switch (getFileExtension(fileName)) {
            case "css":
                return FileType.CSS;
            case "java":
                return FileType.JAVA;
            case "c":
                return FileType.C;
            case "cpp":
                return FileType.CPP;
            case "html":
                return FileType.HTML;
            case "xml":
                return FileType.XML;
            case "text":
                return FileType.TEXT;
            case "rb":
                return FileType.RUBY;
            case "js":
                return FileType.JS;
            case "py":
                return FileType.PYTHON;
            case "pdf":
                return FileType.PDF;
            case "png":
                return FileType.PNG;
            case "jpeg":
            case "jpg":
                return FileType.JPEG;
            case "md":
                return FileType.MARKDOWN;
            default:
                return FileType.DEFAULT;
        }
    }

    public static String getHtmlString(RepoContent r) {

        StringBuilder renderedString = new StringBuilder();

        try {
            if (FileUtils.getFileType(r.getName()) == FileType.MARKDOWN) {
                Log.i("RENDER TEXT: ", "Markdown!!");
                renderedString.append(new Markdown4jProcessor().process(r.getContent()));
            } else {
                String language = "markup";
                switch (FileUtils.getFileType(r.getName())) {
                    case JAVA:
                        language = "java";
                        break;
                    case PYTHON:
                        language = "python";
                        break;
                    case RUBY:
                        language = "ruby";
                        break;
                    case CSS:
                        language = "css";
                        break;
                    case JS:
                        language = "js";
                        break;
                    case C:
                    case CPP:
                        language = "c";
                        break;
                    case JPEG:
                    case PNG:
                        return "IMAGE";
                }
                renderedString.append("<!doctype html>\n")
                        .append("<html lang=\"en\">\n")
                        .append("<head>\n")
                        .append("    <meta charset=\"utf-8\">")
                        .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">")
                        .append("<link rel=\"stylesheet\" href=\"prism.css\">\n")
                        .append("</head>\n")
                        .append("<body>")
                                //.append("<pre><code>")
                                //.append(FileUtils.getFileExtension(r.getName()))
                                //.append("\">")
                        .append(new Markdown4jProcessor().process("```language-" + language + "\n" + r.getContent() + "```"))
                                //.append("</code></pre>")
                        .append("<script src=\"prism.js\"></script>")
                        .append("</body>\n")
                        .append("</html>");
            }
            return renderedString.toString();
        } catch (IOException ex) {
            return null;
        }
    }

    public static String getFileExtension(String fileName) {

        String[] ext =  fileName.split("\\.(?=[^\\.]+$)");

        return (ext.length > 1 ? ext[1] : ext[0]).toLowerCase();
    }
}
