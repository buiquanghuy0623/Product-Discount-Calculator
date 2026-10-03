package com.codegym;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Định tuyến URL nhận request POST từ endpoint /display-discount
@WebServlet(name = "DiscountServlet", urlPatterns = {"/display-discount"})
public class DiscountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Cấu hình hiển thị Tiếng Việt
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            try {
                // 1. Nhận các tham số từ form nhập liệu
                String description = request.getParameter("description");
                double price = Double.parseDouble(request.getParameter("price"));
                double discountPercent = Double.parseDouble(request.getParameter("discount_percent"));
                
                // 2. Tính toán chiết khấu và giá sau chiết khấu
                // Công thức: Discount Amount = List Price * Discount Percent * 0.01
                double discountAmount = price * discountPercent * 0.01;
                double discountPrice = price - discountAmount;
                
                // 3. Hiển thị kết quả ra màn hình
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Discount Calculation Result</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; display: flex; justify-content: center; margin-top: 50px; background-color: #f8fafc; }");
                out.println(".result-container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); width: 400px; }");
                out.println("h2 { color: #1b2a7a; text-align: center; }");
                out.println(".result-item { margin-bottom: 12px; font-size: 16px; }");
                out.println(".label { font-weight: bold; color: #333; }");
                out.println(".value { color: #27ae60; font-weight: bold; }");
                out.println("a { display: inline-block; margin-top: 15px; text-decoration: none; padding: 10px 20px; background-color: #1b2a7a; color: white; border-radius: 4px; text-align: center; width: 90%; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='result-container'>");
                out.println("<h2>Discount Calculation Result</h2>");
                out.println("<div class='result-item'><span class='label'>Product Description: </span>" + description + "</div>");
                out.println("<div class='result-item'><span class='label'>List Price: </span>$" + String.format("%.2f", price) + "</div>");
                out.println("<div class='result-item'><span class='label'>Discount Percent: </span>" + discountPercent + "%</div>");
                out.println("<hr style='border: 0.5px solid #eee; margin: 15px 0;'>");
                out.println("<div class='result-item'><span class='label'>Discount Amount: </span><span class='value'>$" + String.format("%.2f", discountAmount) + "</span></div>");
                out.println("<div class='result-item'><span class='label'>Discount Price: </span><span class='value'>$" + String.format("%.2f", discountPrice) + "</span></div>");
                out.println("<div style='text-align: center;'><a href='index.jsp'>Calculate Again</a></div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
                
            } catch (NumberFormatException e) {
                out.println("<!DOCTYPE html>");
                out.println("<html><body>");
                out.println("<h2 style='color: red; text-align: center; margin-top: 100px;'>Error: Invalid price or discount input!</h2>");
                out.println("<div style='text-align: center;'><a href='index.jsp'>Go Back</a></div>");
                out.println("</body></html>");
            }
        }
    }
}
