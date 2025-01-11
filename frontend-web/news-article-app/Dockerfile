FROM node:18 as build

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build -- --output-path=/usr/src/app/dist/frontend-web

FROM nginx:alpine

RUN rm -rf /usr/share/nginx/html/*

COPY --from=build /usr/src/app/dist/frontend-web/* /usr/share/nginx/html/

COPY nginx.config /etc/nginx/config.d/default.config

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
