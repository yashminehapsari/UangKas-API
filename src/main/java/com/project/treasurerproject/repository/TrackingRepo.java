package com.project.treasurerproject.repository;

import com.project.treasurerproject.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackingRepo extends JpaRepository<Tracking,String> {
    @Query(nativeQuery = true, value = "SELECT t.* FROM t_tracking t JOIN m_accumulation a ON a.id=t.accumulation_id WHERE a.id=:id")
    List<Tracking> findByAccumulationId(@Param("id") String id);
    @Query(nativeQuery = true, value = "SELECT t.* FROM t_tracking t JOIN m_member m ON m.id=t.member_id WHERE m.id=:id")
    List<Tracking> findByMemberId(@Param("id") String id);
    @Query(nativeQuery = true, value = "SELECT t.* FROM t_tracking t JOIN m_member m ON m.id=t.member_id JOIN m_accumulation a ON a.id=t.accumulation_id WHERE m.id=:m_id AND a.id=:a_id")
    Optional<Tracking> findByAccumulationAndMemberId(@Param("a_id") String accumulationId, @Param("m_id") String memberId);

}
