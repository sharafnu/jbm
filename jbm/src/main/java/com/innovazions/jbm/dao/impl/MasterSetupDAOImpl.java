package com.innovazions.jbm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.innovazions.jbm.common.CommonUtils;
import com.innovazions.jbm.dao.MasterSetupDAO;
import com.innovazions.jbm.entity.MasterSetup;
import com.innovazions.jbm.entity.jdbc.mapper.MasterSetupRowMapper;

@Repository
public class MasterSetupDAOImpl implements MasterSetupDAO {

	@Autowired
	private DataSource dataSource;

	@Override
	public long createMasterSetup(MasterSetup masterSetup) {
		String sql = "INSERT INTO master_setup "
				+ "(master_type, code, description) VALUES (?, ?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { masterSetup.getMasterType(),
				masterSetup.getCode(), masterSetup.getDescription() });
		return 0;
	}

	@Override
	public void updateMasterSetup(MasterSetup masterSetup) {
		if (masterSetup != null && masterSetup.getId() != null && masterSetup.getId() > 0) {
			String sql = "UPDATE master_setup SET master_type=?, code=?, description=? where id=?";

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			jdbcTemplate.update(sql, new Object[] {
					masterSetup.getMasterType(), masterSetup.getCode(),
					masterSetup.getDescription(), masterSetup.getId() });
		}
	}

	@Override
	public void deleteMasterSetup(MasterSetup masterSetup) {
		if (masterSetup != null && masterSetup.getId() != null && masterSetup.getId() > 0) {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.execute("delete from master_setup where id="
					+ masterSetup.getId());
		}
	}

	@Override
	public List<MasterSetup> getMasterSetupList(MasterSetup masterSetup) {
		List<MasterSetup> masterSetupList = new ArrayList<MasterSetup>();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String sql="";
		if(masterSetup.getMasterType().equals("All")) {
			sql = "select id, master_type, code, description from "
					+ "master_setup ";
			masterSetupList = jdbcTemplate.query(sql,
					new MasterSetupRowMapper());
		} else {
			sql = "select id, master_type, code, description from "
					+ "master_setup where master_type=?";
			masterSetupList = jdbcTemplate.query(sql,
					new Object[] { masterSetup.getMasterType() },
					new MasterSetupRowMapper());
		}
		
		
		return masterSetupList;
	}

}
