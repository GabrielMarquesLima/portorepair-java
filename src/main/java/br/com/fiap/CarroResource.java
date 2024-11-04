package br.com.fiap;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;


@Path("/carros")
public class CarroResource {
    private CarroDAO carroDAO = new CarroDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCarros() {
        try {
            List<Carro> carros = carroDAO.getAllCarros();
            return Response.ok(carros).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }


    }

    @GET
    @Path("/{numChassi}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarro(@PathParam("numChassi") String numChassi) {
        try {
            Carro carro = carroDAO.getCarro(numChassi);
            if (carro == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(carro).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCarro(Carro carro) {
        try {
            carroDAO.addCarro(carro);
            return Response.status(Response.Status.CREATED).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{numChassi}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCarro(@PathParam("numChassi") String numChassi, Carro carro) {
        try {
            carro.setNumChassi(numChassi);
            carroDAO.updateCarro(carro);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{numChassi}")
    public Response deleteCarro(@PathParam("numChassi") String numChassi) {
        try {
            carroDAO.deleteCarro(numChassi);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}