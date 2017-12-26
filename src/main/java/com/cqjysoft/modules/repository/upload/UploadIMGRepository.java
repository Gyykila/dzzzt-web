package com.cqjysoft.modules.repository.upload;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cqjysoft.modules.entity.upload.UploadIMG;

public interface UploadIMGRepository extends JpaRepository<UploadIMG, Long> {
	List<UploadIMG> findTop8ByIdGreaterThanOrderById(Long id);
}