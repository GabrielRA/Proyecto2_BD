--CRUD EQUIPOS
CREATE PROCEDURE CRUD_Equipo(@accion in int, @codigoPais in Equipo.codigoPais%TYPE,	@nombre in Equipo.nombre%TYPE,	@codigoConfederacionin Equipo.codigoConfederacionin%TYPE, @grupo in Equipo.grupo%TYPE)
    
    
as
BEGIN
	--CREATE
    IF(@accion = 1)
        BEGIN TRY
			BEGIN TRAN
				INSERT INTO Equipo(codigoPais,nombre,codigoConfederacionin,grupo)
				VALUES (@codigoPais,@nombre,@codigoConfederacion,@grupo)
			COMMIT
        END TRY
        BEGIN CATCH
			IF @@TRANCOUNT >0
				--return @@ERROR
				ROLLBACK
        END CATCH
	--READ	
    IF(@accion = 2)
        BEGIN TRY
			Select *
			From Equipo e  
			Where e.codigoPais = @codigoPais
        END TRY
		BEGIN CATCH
			if @@ERROR<>0
				return @@ERROR
		END CATCH
	--UPDATE	
     IF(@accion = 3)
        BEGIN TRY
			BEGIN TRAN
				UPDATE Equipo e
				SET
					codigoPais = NVL2(e.codigoPais,@codigoPais,e.codigoPais),
					nombre = NVL2(e.nombre,@nombre,e.nombre),
					codigoConfederacion = NVL2(e.codigoConfederacion,@codigoConfederacion,e.codigoConfederacion),
					grupo = NVL2(e.grupo,@grupo,e.grupo)
				WHERE e.codigoPais = @codigoPais
			COMMIT
        END TRY
        BEGIN CATCH
			IF @@TRANCOUNT >0
				--return @@ERROR
				ROLLBACK
        END CATCH
	--DELETE	
    IF(@accion = 4)
        BEGIN TRY
			BEGIN TRAN
			Delete from Equipo e
			WHERE e.codigoPais = @codigoPais
			COMMIT
        END TRY
        BEGIN CATCH
			IF @@TRANCOUNT >0
				--return @@ERROR
				ROLLBACK
        END CATCH
END CRUD_Equipo	