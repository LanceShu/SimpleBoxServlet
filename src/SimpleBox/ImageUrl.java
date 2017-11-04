package SimpleBox;

import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import java.io.*;


@WebServlet(name = "ImageUrl",urlPatterns = "/ImageUrl")
public class ImageUrl extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter pw = response.getWriter();

        long id = System.currentTimeMillis();
        ServletInputStream sis = request.getInputStream();

        //将图片上传到服务器的某个文件夹下;
        String pathUrl = "/home/lanceshu/Tomcat/apache-tomcat-8.5.23/webapps/SimpleBox";
        String imageUrl = "/picture/" + id + ".jpg";

        OutputStream os = new FileOutputStream(pathUrl + imageUrl);
        BufferedOutputStream bos = new BufferedOutputStream(os);

        byte[] bytes = new byte[1024];
        int data = 0;
        while((data = sis.read(bytes)) != -1){
            bos.write(bytes,0,data);
        }
        sis.close();
        bos.close();
        os.close();

        File file = new File(pathUrl + imageUrl);
        if(file.exists()){
            pw.println("success");
            pw.println("http://123.207.145.251:8080/SimpleBox" + imageUrl);
        }else{
            pw.println("failure");
        }

    }
}
