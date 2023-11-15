package org.itnow.cursoSpring.clasecuatro.services;

import java.util.*;

public interface CrudService<T> {
		public void registrarNuevo(T nuevo) throws CrudException;
		public void actualizarInfo(T existente) throws CrudException; 
		public void darDeBaja(int id) throws CrudException;
		public List<T> obtenerTodos() throws CrudException;
		public T obtenerPorId(int id) throws CrudException;
}