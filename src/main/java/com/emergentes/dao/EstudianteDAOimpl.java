package com.emergentes.dao;

import com.emergentes.modelo.Estudiante;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOimpl extends ConexionDB implements EstudianteDAO {

    @Override
    public void insert(Estudiante estudiante) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into estudiantes(nombre,apellido,seminario,fecha) VALUES(?,?,?,?)");
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getSeminario());
            ps.setDate(4, estudiante.getFecha());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Estudiante estudiante) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE estudiantes set nombre = ?, apellido = ?, seminario = ?, fecha = ? where id = ?");
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getSeminario());
            ps.setDate(4, estudiante.getFecha());
            ps.setInt(5, estudiante.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM estudiantes WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Estudiante getById(int id) throws Exception {
        Estudiante est = new Estudiante();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM estudiantes WHERE id = ? limit 1");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                est.setId(rs.getInt("id"));
                est.setNombre(rs.getString("nombre"));
                est.setApellido(rs.getString("apellido"));
                est.setSeminario(rs.getString("seminario"));
                est.setFecha(rs.getDate("fecha"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return est;
    }

    @Override
    public List<Estudiante> getAll() throws Exception {
        List<Estudiante> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM estudiantes");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Estudiante>();
            while (rs.next()) {
                Estudiante est = new Estudiante();
                est.setId(rs.getInt("id"));
                est.setNombre(rs.getString("nombre"));
                est.setApellido(rs.getString("apellido"));
                est.setSeminario(rs.getString("seminario"));
                est.setFecha(rs.getDate("fecha"));
                lista.add(est);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

}
