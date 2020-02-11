# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table business (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  country                       bigint,
  city                          bigint,
  category                      bigint,
  user_id                       bigint,
  location                      bigint,
  location_description          longtext,
  latitude                      bigint,
  longitude                     bigint,
  phone1                        bigint,
  phone2                        bigint,
  description                   longtext,
  legal_status                  varchar(255),
  closing_time                  varchar(255),
  working_status                varchar(255),
  opening_hour                  varchar(255),
  website                       longtext,
  constraint pk_business primary key (id)
);

create table business_category (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  content                       longtext,
  constraint pk_business_category primary key (id)
);

create table city (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  country                       bigint,
  constraint pk_city primary key (id)
);

create table country (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  code                          bigint,
  flag                          varchar(255),
  constraint uq_country_name unique (name),
  constraint uq_country_code unique (code),
  constraint pk_country primary key (id)
);

create table coupon (
  id                            bigint auto_increment not null,
  coupon_code                   varchar(255),
  start_validation              datetime(6),
  end_validation                datetime(6),
  coupon_user_id                bigint,
  constraint pk_coupon primary key (id)
);

create table followers (
  id                            bigint auto_increment not null,
  user_id                       bigint,
  business_id                   bigint,
  constraint pk_followers primary key (id)
);

create table location (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  city                          bigint,
  constraint pk_location primary key (id)
);

create table pending_activation (
  id                            bigint auto_increment not null,
  link                          varchar(255),
  email                         varchar(255),
  date                          varchar(255),
  constraint pk_pending_activation primary key (id)
);

create table product (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  business_id                   bigint,
  category_id                   bigint not null,
  tag                           varchar(255),
  product_description           longtext,
  kinyarwanda_description       longtext,
  selling_price                 bigint,
  buying_price                  bigint,
  city                          bigint,
  longitude                     bigint,
  latitude                      bigint,
  deal_type                     varchar(255),
  checkin                       datetime(6),
  checkout                      datetime(6),
  sign                          varchar(255),
  deal_end_status               longtext,
  today_price                   bigint,
  promotion                     longtext,
  sells                         bigint,
  instock                       bigint,
  image                         longtext not null,
  product_city_location_id      bigint,
  sub_categor1                  varchar(255) not null,
  sub_categor2                  varchar(255) not null,
  sub_categor3                  varchar(255),
  last_review                   datetime(6),
  rating                        bigint,
  deal_start_time               datetime(6),
  deal_end_time                 datetime(6),
  product_absolute_link         varchar(255),
  constraint uq_product_product_absolute_link unique (product_absolute_link),
  constraint pk_product primary key (id)
);

create table product_category (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  content                       longtext,
  classification                varchar(255),
  constraint pk_product_category primary key (id)
);

create table product_image (
  id                            bigint auto_increment not null,
  name                          longtext,
  image                         bigint,
  constraint pk_product_image primary key (id)
);

create table product_option (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  content                       longtext,
  selling_price                 bigint,
  buying_price                  bigint,
  productid                     bigint,
  constraint pk_product_option primary key (id)
);

create table product_sub_category (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  category_id                   bigint,
  constraint pk_product_sub_category primary key (id)
);

create table rating (
  id                            bigint auto_increment not null,
  review                        longtext,
  user_id                       bigint,
  count                         bigint,
  constraint pk_rating primary key (id)
);

create table token (
  id                            bigint auto_increment not null,
  token                         varchar(255),
  timestamp                     varchar(255),
  status                        varchar(255),
  constraint uq_token_token unique (token),
  constraint pk_token primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  fname                         varchar(255) not null,
  lname                         varchar(255) not null,
  username                      longtext,
  email                         varchar(255) not null,
  phone                         bigint,
  password                      varchar(255) not null,
  country                       varchar(255),
  city                          varchar(255),
  location                      varchar(255),
  token                         varchar(255),
  constraint uq_user_email unique (email),
  constraint uq_user_phone unique (phone),
  constraint uq_user_token unique (token),
  constraint pk_user primary key (id)
);

create table user_preference (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  user_id                       bigint,
  constraint pk_user_preference primary key (id)
);

alter table product add constraint fk_product_product_city_location_id foreign key (product_city_location_id) references location (id) on delete restrict on update restrict;
create index ix_product_product_city_location_id on product (product_city_location_id);


# --- !Downs

alter table product drop foreign key fk_product_product_city_location_id;
drop index ix_product_product_city_location_id on product;

drop table if exists business;

drop table if exists business_category;

drop table if exists city;

drop table if exists country;

drop table if exists coupon;

drop table if exists followers;

drop table if exists location;

drop table if exists pending_activation;

drop table if exists product;

drop table if exists product_category;

drop table if exists product_image;

drop table if exists product_option;

drop table if exists product_sub_category;

drop table if exists rating;

drop table if exists token;

drop table if exists user;

drop table if exists user_preference;

