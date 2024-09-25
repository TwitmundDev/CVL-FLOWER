## build stage
#FROM node:lts-alpine as build-stage
#WORKDIR /app
#COPY package*.json ./
#RUN npm install
#COPY . .
#RUN npm run build
#FROM nginx:stable-alpine as production-stage
#COPY --from=build-stage /app/dist /usr/share/nginx/html
#COPY ./ngnix/default.conf /etc/nginx/conf.d/default.conf
#EXPOSE 80
#CMD ["nginx", "-g", "daemon off;"]
FROM node:16
WORKDIR /app
# install vite globally
RUN npm install -g vite
# copy all filtes
COPY . .
# install all deps
RUN npm install
RUN vite build
# vite default port
EXPOSE 5173
CMD ["vite", "preview"]