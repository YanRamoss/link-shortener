FROM node:18 AS build

WORKDIR /app

COPY client/package*.json ./

RUN npm install

COPY client/ ./

RUN npm run build

FROM busybox:1.35.0-uclibc

WORKDIR /app

COPY --from=build /app/dist /var/www/linkshortener.yanramos.com.br/

CMD ["tail", "-f", "/dev/null"]