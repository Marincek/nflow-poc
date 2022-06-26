IF NOT EXISTS (SELECT * FROM sys.databases where name ='microbatch')
BEGIN
    EXEC('CREATE DATABASE microbatch')
END
GO