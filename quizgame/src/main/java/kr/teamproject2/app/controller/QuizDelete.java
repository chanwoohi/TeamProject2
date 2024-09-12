package kr.teamproject2.app.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.service.QuizService;
import kr.teamproject2.app.service.QuizServiceImp;

@WebServlet("/quiz2/quizdelete")
public class QuizDelete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private QuizService quizService = new QuizServiceImp();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 여러 개의 qu_num 값을 배열로 받습니다.
        String[] qu_nums = request.getParameterValues("qu_num");

        if (qu_nums != null) {
            for (String qu_num : qu_nums) {
                // 각 퀴즈를 삭제합니다.
                quizService.deleteQuiz(qu_num);
                boolean result = quizService.deleteAll(qu_nums);
            }

            // 삭제 성공 메시지
            request.setAttribute("msg", "퀴즈 삭제 성공");
        } else {
            // 삭제 실패 메시지
            request.setAttribute("msg", "퀴즈 삭제 실패");
        }

        // 메시지 페이지로 리다이렉트
        request.setAttribute("url", "/admin/quiz");
        request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
    }
}