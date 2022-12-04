package com.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BoardDAO {
	@Autowired
	private JdbcTemplate template;

//	Connection conn = null;
//	PreparedStatement stmt = null;
//	ResultSet rs = null;
  	public void setTemplate(JdbcTemplate template) {this.template = template;}

	private final String BOARD_INSERT = "insert into BOARD (title, writer, content) values (?,?,?)";
	private final String BOARD_UPDATE = "update BOARD set title=?, writer=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete from BOARD  where seq=?";

	//글 추가
	public int insertBoard(BoardVO vo) {
		return template.update(BOARD_INSERT,
				new Object[]{vo.getTitle(), vo.getWriter(), vo.getContent()});
	}

	// 글 삭제
	public int deleteBoard(int id) {
		return template.update(BOARD_DELETE,
				new Object[]{id});
	}

	//글 수정
	public int updateBoard(BoardVO vo) {
		return template.update(BOARD_UPDATE,
				new Object[]{vo.getTitle(), vo.getWriter(), vo.getContent(), vo.getSeq()});
	}

	//원하는 데이터 하나 정보
	private final String BOARD_GET = "select * from BOARD  where seq=?";
	public BoardVO getBoard(int seq) {
		return template.queryForObject(BOARD_GET,
				new BeanPropertyRowMapper<BoardVO>(BoardVO.class),
				new Object[] {seq}
				);
	}

	//데이터 리스트 전체 정보
	private final String BOARD_LIST = "select * from BOARD order by seq desc";
	public List<BoardVO> getBoardList() {
		return template.query(BOARD_LIST, new RowMapper<BoardVO>() {
			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO data = new BoardVO();
				data.setSeq(rs.getInt("seq"));
				data.setTitle(rs.getString("title"));
				data.setWriter(rs.getString("writer"));
				data.setContent(rs.getString("content"));
				data.setRegdate(rs.getDate("regdate"));
				return data;
			}
		});
	}
}

