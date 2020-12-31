package it.solvingteam.padelmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.solvingteam.padelmanagement.model.notice.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{

	List<Notice> findAllNoticeByClub_Id(Long id);

}
