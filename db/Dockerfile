FROM postgres:16

# copy sql statement to the container
COPY init.sql /docker-entrypoint-initdb.d/

# environment variable
ENV POSTGRES_DB=todo_db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=1234
