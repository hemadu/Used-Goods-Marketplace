package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import cn.util.Const;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//返回JSON
		response.setContentType("application/json;charset=UTF-8");
		JSONObject result=new JSONObject();
		PrintWriter out = response.getWriter();
		try {
			// 之前的request.getParameter就获取不到值了
			// 产生一个对象
			SmartUpload su = new SmartUpload();
			// 初始化
			su.initialize(getServletConfig(), request, response);
			// 设置编码格式
			su.setCharSet("UTF-8");
			// 设置上传的配置...
			// 开始上传
			su.upload();
			// 获取服务器的真实目录
			String realPath = getServletContext().getRealPath("/images");
			// 获取文件名称
			String fileName = "";
			Files files = su.getFiles();
			if (files != null) {
				File file = files.getFile(0);
				// 存在文件？？？
				if (!file.isMissing()) {
					// 文件另存为
					fileName = file.getFileName();// 文件名 1.jpg
					// 获取扩展名
					// String
					// exeName=fileName.substring(fileName.lastIndexOf("."));
					// 新的文件名
					// fileName=Const.getFileId()+exeName;
					file.saveAs(realPath + "/" + fileName);
					
					String[] data={Const.ROOT+"images/"+fileName};
					
					result.put("errno", 0);
					result.put("data", data);
					result.put("filename", fileName);
				}
			}
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("errno", 1);
		}
		out.print(JSONObject.toJSONString(result));
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
