-- customer
create table customer
(
    id             bigint auto_increment primary key comment 'ID',
    customer_token varchar(255) not null comment 'customer_token',
    customer_name  varchar(255) not null comment '고객명',
    phone_number   varchar(255) not null comment 'phone_number',
    status         varchar(30)  not null default 'ENABLE' comment '상태',
    created_at     datetime(6) not null comment '생성 일시',
    updated_at     datetime(6) null comment '수정 일시'
) comment 'customer' charset = utf8mb4;

create
index customer_idx01 on customer (customer_token);

create
index customer_idx02 on customer (created_at);

create
index customer_idx03 on customer (updated_at);

-- account
create table accounts
(
    id         bigint auto_increment primary key comment 'ID',
    account_token varchar(255) not null comment 'account_token',
    customer_id bigint       not null comment '고객 ID',
    account_name  varchar(255) not null comment '계좌명',
    account_amt int(11) not null comment '금액',
    status     varchar(30)  not null default 'PREPARE' comment '상태',
    deleted_at datetime(6) null comment '삭제 일시',
    created_at datetime(6) not null comment '생성 일시',
    updated_at datetime(6) null comment '수정 일시'
) comment 'accounts' charset = utf8mb4;

create
index account_idx01 on accounts (account_token);

create
index account_idx02 on accounts (customer_id);

create
index account_idx03 on accounts (created_at);

create
index account_idx04 on accounts (updated_at);

create
index account_idx05 on accounts (deleted_at);


-- account_option_group
create table account_option_groups
(
    id                     bigint auto_increment primary key comment 'ID',
    account_id                bigint      not null comment '계좌 ID',
    ordering               tinyint(3) not null comment '정렬순서',
    account_option_group_name varchar(30) not null comment '옵션그룹명 (색상, 사이즈 등)',
    created_at             datetime(6) not null comment '생성 일시',
    updated_at             datetime(6) null comment '수정 일시'
) comment 'account_option_groups' charset = utf8mb4;

create
index account_option_group_idx01 on account_option_groups (account_id);

create
index account_option_group_idx02 on account_option_groups (created_at);

create
index account_option_group_idx03 on account_option_groups (updated_at);


-- account_option
create table account_options
(
    id                   bigint auto_increment primary key comment 'ID',
    account_option_group_id bigint      not null comment '계좌 옵션 그룹 ID',
    ordering             tinyint(3) not null comment '정렬순서',
    account_option_name     varchar(30) not null comment '옵션명 (빨강, 파랑, XL, L)',
    account_option_amt    int(11) not null comment '계좌 옵션 금액',
    created_at           datetime(6) not null comment '생성 일시',
    updated_at           datetime(6) null comment '수정 일시'
) comment 'account_options' charset = utf8mb4;

create
index account_option_idx01 on account_options (account_option_group_id);

create
index account_option_idx02 on account_options (created_at);

create
index account_option_idx03 on account_options (updated_at);


-- order
create table orders
(
    id                bigint auto_increment primary key comment 'ID',
    order_token       varchar(255) not null comment 'order_token',
    user_id           bigint       not null comment '유저 ID',
    pay_method        varchar(30)  not null comment '결제수단',
    receiver_name     varchar(30)  not null comment '수령자명',
    receiver_phone    varchar(30)  not null comment '수령자 휴대폰번호',
    receiver_zipcode  varchar(10)  not null comment '수령자 우편번호',
    receiver_address1 varchar(255) not null comment '수령자 주소1',
    receiver_address2 varchar(255) not null comment '수령자 주소2',
    etc_message       varchar(255) not null comment '남기는 말',
    status            varchar(30)  not null default 'INIT' comment '상태',
    ordered_at        datetime(6) not null comment '주문 일시',
    created_at        datetime(6) not null comment '생성 일시',
    updated_at        datetime(6) null comment '수정 일시'
) comment 'orders' charset = utf8mb4;

create
index order_idx01 on orders (order_token);

create
index order_idx02 on orders (user_id);

create
index order_idx03 on orders (ordered_at);

create
index order_idx04 on orders (created_at);

create
index order_idx05 on orders (updated_at);


-- order_accounts
create table order_accounts
(
    id              bigint auto_increment primary key comment 'ID',
    order_id        bigint       not null comment 'order_id',
    order_count     tinyint      not null comment '주문갯수',
    customer_id      bigint       not null comment '고객 ID',
    account_id         bigint       not null comment '상품 ID',
    account_name       varchar(255) not null comment '상품명',
    account_token      varchar(30)  not null comment '상품 token',
    account_amt      int(11) not null comment '상품 가격',
    delivery_status varchar(30)  not null default 'BEFORE_DELIVERY' comment '상태',
    created_at      datetime(6) not null comment '생성 일시',
    updated_at      datetime(6) null comment '수정 일시'
) comment 'order_accounts' charset = utf8mb4;

create
index order_account_idx01 on order_accounts (order_id);

create
index order_account_idx02 on order_accounts (customer_id);

create
index order_account_idx03 on order_accounts (account_id);

create
index order_account_idx04 on order_accounts (account_token);

create
index order_account_idx05 on order_accounts (created_at);

create
index order_account_idx06 on order_accounts (updated_at);


-- order_account_option_groups
create table order_account_option_groups
(
    id                     bigint auto_increment primary key comment 'ID',
    order_account_id          bigint       not null comment 'order_account_id',
    ordering               tinyint(3) not null comment '정렬순서',
    account_option_group_name varchar(255) not null comment '상품 옵션 그룹명',
    created_at             datetime(6) not null comment '생성 일시',
    updated_at             datetime(6) null comment '수정 일시'
) comment 'order_account_option_groups' charset = utf8mb4;

create
index order_account_option_groups_idx01 on order_account_option_groups (order_account_id);

create
index order_account_option_groups_idx02 on order_account_option_groups (created_at);

create
index order_account_option_groups_idx03 on order_account_option_groups (updated_at);


-- order_account_options
create table order_account_options
(
    id                         bigint auto_increment primary key comment 'ID',
    order_account_option_group_id bigint       not null comment 'order_account_option_group_id',
    ordering                   tinyint(3) not null comment '정렬순서',
    account_option_name           varchar(255) not null comment '상품 옵션명',
    account_option_amt          int(11) not null comment '상품 옵션 가격',
    created_at                 datetime(6) not null comment '생성 일시',
    updated_at                 datetime(6) null comment '수정 일시'
) comment 'order_account_options' charset = utf8mb4;

create
index order_account_options_idx01 on order_account_options (order_account_option_group_id);

create
index order_account_options_idx02 on order_account_options (created_at);

create
index order_account_options_idx03 on order_account_options (updated_at);
