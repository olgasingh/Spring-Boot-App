package com.example.demo.Repositories;

import java.util.List;

import com.example.demo.Models.Stat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatRepository extends JpaRepository<Stat, Long> , StatRepositoryCustom{
 

    @Query(value = "select sum(s.point) pt, s.playerid, t.name from stat s" +
    " inner join coach c on ( s.coachid= c.id) inner join team t on (c.teamid= t.id)" +
    " group by s.playerid, t.name order by pt desc limit 5", nativeQuery = true)
    List<Object[]> getMaxAgeMinus();
}