package com.honeycombee.web.artdental.mvc;

import com.honeycombee.web.artdental.domain.Member;
import com.honeycombee.web.artdental.domain.TestBoard;
import com.honeycombee.web.artdental.domain.TestBoardComment;
import com.honeycombee.web.artdental.repository.MemberRepository;
import com.honeycombee.web.artdental.repository.TestBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by gizmo4u on 15. 5. 31..
 */
@Controller
public class ProtoController {

    @Autowired
    private TestBoardRepository testBoardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @ModelAttribute("testBoardList")
    public List<TestBoard> populateTestBoardList(){
        return this.testBoardRepository.findAll();
    }

    @ModelAttribute("testMember")
    public Member populateTestMember(){
        return this.memberRepository.findByEmail("soonystory@naver.com");
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/saveBoard", method = RequestMethod.GET)
    public String saveBoardForm(Model model){

        TestBoard saveTestBoard = new TestBoard();
        saveTestBoard.setChecked(false);
        saveTestBoard.setContent("<h3>Lorem Ipsum is simply</h3>\n" +
                "                                    dummy text of the printing and typesetting industry. <strong>Lorem Ipsum has been the industry's</strong> standard dummy text ever since the 1500s,\n" +
                "                                    when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic\n" +
                "                                    typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with\n" +
                "                                    <br/>\n" +
                "                                    <br/>\n" +
                "                                    <ul>\n" +
                "                                        <li>Remaining essentially unchanged</li>\n" +
                "                                        <li>Make a type specimen book</li>\n" +
                "                                        <li>Unknown printer</li>\n" +
                "                                    </ul>");
        model.addAttribute("testBoard", saveTestBoard);
        return "saveBoard";
    }

    @RequestMapping(value = "/saveBoard", method = RequestMethod.POST, params = {"save"})
    public String saveBoard(@Valid final TestBoard testBoard,
                            final BindingResult bindingResult, final ModelMap model){

        TestBoard tempTestBoard = null;

        if(bindingResult.hasErrors()){
            return "/saveBoard";
        }

        //게시물 저장
        /*if(testBoard.hasId()) {
            System.out.println(testBoard.getId());
            tempTestBoard = this.testBoardRepository.findOne(testBoard.getId());
            this.testBoardRepository.delete(testBoard.getId());
        }

        if(tempTestBoard!=null){
            testBoard.setId(tempTestBoard.getId());
            testBoard.setCreateDate(tempTestBoard.getCreateDate());
        }else{
            testBoard.setId(null);
            testBoard.setCreateDate(new Date());
        }*/
        if(!testBoard.hasId()) {
            System.out.println("id null : " + testBoard.getId());
            testBoard.setId(null);
            testBoard.setCreateDate(new Date());
        }else{
            tempTestBoard = this.testBoardRepository.findOne(testBoard.getId());
            System.out.println("id exist : " + tempTestBoard);
            testBoard.setChecked(tempTestBoard.isChecked());
            testBoard.setComments(tempTestBoard.getComments());
            testBoard.setCreateDate(tempTestBoard.getCreateDate());
        }

        testBoard.setWriter(populateTestMember());
        testBoard.setUpdateDate(new Date());
        this.testBoardRepository.save(testBoard);

        //댓글 저장
        //기존 글에서 댓글리스트 저장후 신규 댓글 추가해서 저장



        model.clear();
        return "redirect:/index";
    }

    @RequestMapping(value = "/showBoard/{id}")
    public String showBoard(@PathVariable String id, Model model){

        System.out.println("showBoard enter : " + id);

        TestBoard showBoard = testBoardRepository.findOne(id);
        showBoard.setChecked(true);
        testBoardRepository.save(showBoard);

        System.out.println(showBoard.getTitle());

        model.addAttribute("board", showBoard);
        model.addAttribute("comment", new TestBoardComment());
        return "showBoard";
    }

    @RequestMapping(value = "/editBoard/{id}", method = RequestMethod.GET, params = "edit")
    public String editBoardForm(@PathVariable("id") String id, Model model){

        TestBoard saveTestBoard = testBoardRepository.findOne(id);
        model.addAttribute("testBoard", saveTestBoard);

        return "saveBoard";
    }

    @RequestMapping(value = "/editBoard/{id}", method = RequestMethod.GET, params = "delete")
    public String deleteBoardForm(@PathVariable("id") String id, ModelMap model){

        this.testBoardRepository.delete(id);
        model.clear();
        return "redirect:/index";
    }

    @RequestMapping(value = "/editBoard/{id}", method = RequestMethod.POST, params = "saveComment")
    public String saveComment(@PathVariable("id") String id,
                              final TestBoardComment comment, ModelMap model){

        //transaction required !!
        TestBoard testBoard = this.testBoardRepository.findOne(id);

        comment.setId("" + (testBoard.getComments().isEmpty() ? 1 : testBoard.getComments().size() + 1));
        comment.setCreateDate(new Date());
        comment.setCommenter(populateTestMember());
        testBoard.getComments().add(comment);

        this.testBoardRepository.save(testBoard);

        model.clear();
        return "redirect:/showBoard/"+id;
    }

    @RequestMapping(value = "/editBoard/{id}/{commentId}", method = RequestMethod.POST, params = "deleteComment")
    public String deleteComment(@PathVariable("id") String id, @PathVariable("commentId") String commentId,
                                final TestBoardComment comment, ModelMap model){

        //transaction required !!
        TestBoard testBoard = this.testBoardRepository.findOne(id);

        System.out.println("before delete :" + testBoard.getComments());
        testBoard.removeCommentById(commentId);
        System.out.println("after delete :" + testBoard.getComments());

        this.testBoardRepository.save(testBoard);

        model.clear();
        return "redirect:/showBoard/"+id;
    }



}
