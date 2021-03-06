package com.jaymen.candidate.dao.hibernate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.jaymen.candidate.dao.CandidateDao;
import com.jaymen.candidate.domain.Candidate;

public class CandidateHibernateDao extends HibernateDaoSupport implements
		CandidateDao {
	
	//Use slf4j for logging purposes
	private static Logger logger = LoggerFactory.getLogger(CandidateHibernateDao.class);
	
	@SuppressWarnings("unchecked")
	public List<Candidate> findCandidatesByName(String name) {
		logger.debug("searching for candidates matching name: " + name);
		return this.getHibernateTemplate().find("FROM com.jaymen.candidate.domain.Candidate candidate " +
				"WHERE lower(name) like ?", name.toLowerCase());
	}

	public Candidate getCandidate(Integer id) {
		logger.debug("finding candidate with id: " + id);
		return (Candidate) this.getHibernateTemplate().get(Candidate.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Candidate> getCandidates() {
		logger.debug("getting all candidates");
		return this.getHibernateTemplate().find("FROM com.jaymen.candidate.domain.Candidate candidate");
	}

	public Candidate insertCandidate(Candidate candidate) {
		logger.debug("adding a new candidate");
		this.getHibernateTemplate().saveOrUpdate(candidate);
		return candidate;
	}

	@Transactional
	public Boolean removeCandidate(Integer id) {
		logger.debug("removing candidate with id: " + id);
		this.getHibernateTemplate().delete(getCandidate(id));
		return true;
	}

	public Candidate updateCandidate(Candidate candidate) {
		logger.debug("updating candidate with id: " + candidate.getId());
		return this.insertCandidate(candidate);
	}

}
