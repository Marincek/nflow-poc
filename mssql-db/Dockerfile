FROM mcr.microsoft.com/mssql/server

# Switch to root user for access to apt-get install
USER root

# Install node/npm
RUN apt-get -y update  && \apt-get install -y dos2unix

# Bundle app source
COPY . .

RUN dos2unix *

# Grant permissions for the import-data script to be executable
RUN chmod +x ./import-data.sh

EXPOSE 8080

# Switch back to mssql user and run the entrypoint script
USER mssql
ENTRYPOINT /bin/bash ./entrypoint.sh
