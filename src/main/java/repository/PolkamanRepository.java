package repository;

import java.util.List;

import model.Polkaman;

public interface PolkamanRepository {
	List<Polkaman> findAll();
	//void save(Polkaman polkaman);
	void deleteById(int id);
}
