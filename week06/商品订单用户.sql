create schema if not exists geek_java collate latin1_swedish_ci;

create table if not exists order_info
(
	id bigint null,
	order_no varchar(32) null comment '订单号',
	out_order_no varchar(32) null comment '外部订单号',
	member_no varchar(100) null comment '会员编号',
	real_name varchar(100) null comment '真实姓名',
	identity_card varchar(64) null comment '身份证编号',
	order_source varchar(10) null comment '订单来源',
	order_type varchar(10) null comment '订单类型',
	sale_time datetime null comment '销售时间',
	order_status varchar(10) null comment '订单状态',
	order_status_desc varchar(10) null comment '订单状态描述',
	pay_status varchar(10) null comment '支付状态',
	pay_time timestamp null comment '支付时间',
	delivery_mode varchar(10) null comment '配送方式',
	need_invoice int null comment '是否开发票',
	invoice_info varchar(1000) null comment '发票信息json
',
	need_send_cost decimal(10,2) null comment '应收运费',
	send_cost decimal(10,2) null comment '实际运费',
	sales_amount decimal(20,2) null comment '商品销售总额',
	payment_amount decimal(20,2) null comment '订单应付金额',
	cash_amount decimal(20,2) null comment '订单现金类支付金额',
	cash_income decimal(10,2) null comment '收银损益',
	promotion_amount decimal(20,2) null comment '订单优惠总额',
	coupon_amount decimal(20,2) null comment '使用优惠券金额',
	integral decimal(20,2) null comment '积分',
	customer_comments varchar(1000) null comment '客户备注',
	call_center_comments varchar(1000) null comment '客服备注',
	recept_phone varchar(64) null comment '收件人电话',
	recept_name varchar(64) null comment '收件人姓名',
	recept_prov_no varchar(32) null comment '收件地区省份编号',
	recept_prov_name varchar(64) null comment '收件地区省份名称',
	recept_city_no varchar(32) null comment '收件人城市编号',
	recept_city_name varchar(64) null comment '收件人城市名称',
	recept_address varchar(500) null comment '收货地址',
	recept_district_no varchar(32) null comment '收件人区编码',
	recept_district_name varchar(64) null comment '收件人区名称',
	extract_flag int null comment '提货类型',
	is_cod int null comment '是否货到付款',
	created_time datetime null comment '创建时间
',
	latest_update_time datetime null comment '最后修改时间
'
);

create table if not exists order_item
(
	sid bigint auto_increment
		primary key,
	order_no varchar(32) null comment '订单号',
	row_no int null comment '行号',
	order_item_no varchar(32) not null comment '商品行项目编号',
	item_status varchar(32) null comment '订单明细状态',
	shoppe_pro_name varchar(255) null comment '商品名称',
	store_no varchar(32) null comment '门店编号',
	store_name varchar(255) null comment '门店名称',
	supply_code varchar(200) null comment '供应商编码（组合码原sku）',
	supplly_name varchar(255) null comment '供应商名称',
	unit varchar(10) null comment '商品单位',
	brand_no varchar(32) null comment '中台品牌',
	brand_name varchar(255) null comment '中台品牌名称',
	barcode varchar(255) null comment '条形码',
	spu_no varchar(32) null comment '产品编号',
	sku_no varchar(32) null comment 'sku编号',
	color_no varchar(200) null comment '颜色',
	color_name varchar(255) null comment '颜色名称',
	size_no varchar(40) null comment '规格（证书编码）',
	size_name varchar(255) null comment '规格名称（赠品的活动id）',
	stand_price decimal(20,2) null comment '商品标准价',
	sales_price decimal(20,2) null comment '商品售价',
	price_type varchar(32) null comment '价格类型',
	refund_num int default 0 null comment '退货数量',
	allow_refund_num int null comment '允许退货数量',
	stockout_amount int(20) default 0 null comment '缺货数量',
	sale_sum int null comment '销售数量',
	pick_sum int null comment '提货数量',
	payment_amount decimal(10,2) null comment '商品折后总额',
	cash_amount decimal(10,2) null comment '现金类支付金额',
	total_discount decimal(10,2) null comment '商品优惠金额',
	shipping_attribute varchar(32) null comment '物流属性',
	product_name varchar(255) null comment '商品名称',
	is_gift varchar(2) null comment '是否为赠品',
	url varchar(255) null comment '商品图片地址',
	business_mode varchar(32) null comment '经营方式',
	shipping_fee_split decimal(20,2) null comment '运费分摊金额',
	delivery_shipping_fee_split decimal(20,2) null comment '实际运费分摊',
	invoice_amount decimal(20,2) null comment '发票金额',
	manger_cate_no varchar(32) null comment '管理分类',
	is_pay_reduce varchar(10) null comment '是否支付减（默认0：否；1：是）',
	created_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
	latest_update_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '最后更新时间',
	latest_update_man varchar(32) null comment '最后更新人'
);

create table if not exists order_package
(
	sid bigint auto_increment
		primary key,
	order_no varchar(64) null comment '订单号',
	package_no varchar(64) null comment '包裹单号',
	del_com_name varchar(64) null comment '快递公司',
	del_com_no varchar(64) null comment '快递公司编号',
	delivery_no varchar(64) null comment '快递单号',
	create_time datetime null comment '创建时间',
	send_time datetime null comment '发货时间',
	ext_place_no varchar(64) null comment '自提点编号',
	ext_place_name varchar(64) null comment '自提点名称',
	sign_time datetime null comment '签收时间',
	sign_name varchar(64) null comment '签收人',
	package_status varchar(64) null comment '包裹状态',
	package_status_desc varchar(100) null comment '包裹状态描述',
	refund_address varchar(64) null comment '退货地址',
	send_to_erp varchar(2) default '0' null comment '来自erp',
	send_to_tms varchar(2) default '0' null comment '来自tms',
	send_to_third varchar(2) default '0' null comment '来自快递鸟',
	sale_no varchar(64) null comment '销售单号',
	package_source varchar(64) null comment '包裹来源',
	pos_money varchar(64) null comment '刷POS金额',
	cash_money varchar(64) null comment '现金'
)
comment '快递包裹单' charset=utf8;

create table if not exists order_package_history
(
	sid bigint auto_increment
		primary key,
	order_no varchar(64) null comment '订单号',
	package_no varchar(64) null comment '包裹单号',
	package_status varchar(10) null comment '包裹单状态',
	package_status_desc varchar(64) null comment '包裹单状态描述',
	delivery_no varchar(64) null comment '快递单号',
	delivery_date_str varchar(400) null comment '快递记录时间',
	delivery_record varchar(4000) null comment '快递记录',
	delivery_man varchar(255) null comment '快递员',
	delivery_man_no varchar(64) null comment '快递员编号',
	create_time datetime null comment '创建时间',
	update_man varchar(32) null comment '更新人',
	remark varchar(255) null comment '备注',
	operator_source varchar(20) null comment '更新系统来源'
)
comment '包裹历史表' charset=utf8;

create table if not exists order_package_item
(
	sid bigint auto_increment
		primary key,
	package_no varchar(64) null comment '包裹单号',
	delivery_no varchar(64) null comment '快递单号',
	sale_no varchar(64) null comment '销售单号',
	sale_item_no varchar(64) null comment '销售单明细号',
	sale_num int null comment '销售数量'
)
comment '包裹明细' charset=utf8;

create table if not exists order_payment
(
	sid bigint auto_increment
		primary key,
	sales_payment_no varchar(60) null comment '款机流水单号',
	money decimal(20,2) null comment '总金额',
	pay_flow_no varchar(60) null comment '交易流水号',
	oo_flag varchar(2) null comment '线上线下标识:0——线下；1——线上',
	pay_time datetime null comment '支付时间',
	casher varchar(225) null comment '收银员',
	cash_amount decimal(20,2) null comment '现金类支付金额',
	order_no varchar(32) null comment '线上订单号',
	check_time datetime null comment '对账时间',
	delete_flag varchar(2) null comment '作废标志'
)
charset=utf8;

create table if not exists order_payment_item
(
	sid bigint auto_increment
		primary key,
	pos_flow_no varchar(60) null comment '交易支付流水',
	payment_class varchar(10) null comment '一级支付介质',
	payment_type varchar(10) null comment '支付方式',
	amount decimal(20,2) null comment '支付金额',
	actural_amount decimal(20,2) null comment '实际抵扣金额',
	rate decimal(20,2) null comment '汇率（折现率)',
	account varchar(32) null comment '支付账号',
	user_id varchar(32) null comment '会员id',
	pay_flow_no varchar(32) null comment '支付流水号',
	coupon_type varchar(32) null comment '优惠券类型',
	coupon_batch varchar(32) null comment '优惠券批次',
	coupon_name varchar(32) null comment '券模板名称',
	activity_no varchar(32) null comment '活动号',
	coupon_rule varchar(32) null comment '收券规则',
	coupon_rule_name varchar(255) null comment '收券规则描述',
	remark varchar(255) null comment '备注',
	cash_balance decimal(10,2) null comment '结余'
)
charset=utf8;

create table if not exists pcm_price
(
	sid bigint auto_increment comment '价格表SID'
		primary key,
	shoppe_pro_sid varchar(50) null comment '条码编号',
	promotion_price decimal(10,2) null comment '促销价格',
	current_price decimal(10,2) null comment '现价',
	original_price decimal(10,2) null comment '原价',
	off_value decimal(10,2) null comment '折扣值，后台程序计算 promotion_price/original_price',
	promotion_begin_time datetime null comment '促销开始时间',
	promotion_end_time datetime null comment '促销结束时间',
	price_type int default 1 null comment '价格类型：1零售价 2 短期价',
	attribute1 varchar(50) null comment '变价编号',
	ifdel int(1) default 0 null comment '是否删除：0：正常，1：删除',
	product_sid bigint null comment '商品SID',
	promotion_backup decimal null,
	channel_sid varchar(10) default '0' null comment '渠道sid',
	unit varchar(20) null comment '货币单位',
	price_channel varchar(30) null,
	promotion_no int(4) null comment '促销编号',
	system_time timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '系统时间',
	attribute2 varchar(50) null comment '门店编码',
	attribute3 varchar(50) null,
	attribute4 varchar(50) null,
	attribute5 varchar(50) null,
	in_purchase_price decimal(10,2) null
)
comment '商品价格表' charset=utf8;

create table if not exists pcm_pro_detail
(
	sid bigint auto_increment comment '商品明细SID'
		primary key,
	product_detail_sid varchar(20) null comment '商品明细表编码',
	pro_detail_name varchar(200) null comment 'SKU标准品名',
	product_sid bigint null comment '产品表编码',
	pro_color_alias varchar(255) null comment '色码名称',
	features varchar(20) null comment '特性',
	article_num varchar(20) null comment '货号',
	memo varchar(400) null comment '商品颜色为图片颜色',
	barcode varchar(255) null comment '条码',
	pro_wri_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '录入时间',
	photo_sale_code_sid varchar(20) null comment '送去拍照的销售编码SID',
	photo_status int(4) default 0 null comment '照片状态：0未拍照 1拍照系统已获取 3已拍照已上传 4已编辑 默认 0',
	opt_user_sid bigint null comment '操作用户SID',
	opt_real_name varchar(40) null comment '操作用户real_name',
	opt_update_time timestamp null comment '操作时间',
	plan_maker varchar(255) null comment '拍照计划制定人',
	plan_time timestamp null comment 'SKU上架时间',
	photo_plan_sid varchar(20) default '0' null comment '拍照上线计划(sku):0未加入上线计划;1已加入上线计划;2发布上线计划 默认 0',
	pro_active_bit int(4) default 1 null comment '是否启用  1 启用 0 未启用',
	pro_type int(4) default 1 null comment '商品类型 ：',
	selling_status int(4) default 0 null comment '上架状态:0 未上架，1 已上架，2 已下架',
	search_key varchar(255) null comment '关键字',
	key_word varchar(255) null comment '活动关键字',
	size_picture_url varchar(255) default '1' null comment '库存状态：0有货，1无货',
	jud_exist varchar(4) null comment '物料类型',
	mtart int null comment '物料类型 HXSP，HXJL,HXPL,HXFL,HXZ',
	price decimal null,
	constraint IDX_PRODUCT_DETAIL_SID
		unique (product_detail_sid),
	constraint IDX_SID
		unique (product_sid, pro_color_alias)
)
comment '商品明细表（SKU）' charset=utf8;

create table if not exists pcm_product
(
	sid bigint auto_increment comment '商品sid'
		primary key,
	product_sid varchar(20) null comment '产品表sid',
	product_name varchar(255) null comment 'SPU标准品名',
	primary_attr varchar(20) null comment '主属性',
	product_sku varchar(100) null comment '商品SKU(款号)',
	brand_sid varchar(20) null comment '品牌编码',
	brand_root_sid varchar(20) null comment '品牌sid',
	brand_name varchar(255) null comment '品牌名称',
	pro_active_bit int(4) default 1 null comment '是否启用:0未启用 1启用 默认 1',
	pro_selling int(4) default 0 null comment '是否上架:0未上架 1上架 2已下架 默认 0',
	activity_flg int(4) null comment '活动标志：1非活动；活动的值根据活动接口类型待定（活动系统舍弃，走促销系统）',
	pro_picture varchar(255) null comment '商品图片',
	category_sid bigint null comment '品类SID   (暂时不用)',
	sex_sid int(4) default 0 null comment '适合性别',
	create_time timestamp default CURRENT_TIMESTAMP not null,
	awesome bigint default 0 null comment '赞',
	pro_desc_sid bigint null comment '商品页描述id',
	product_name_alias varchar(255) null comment '商品别名',
	pro_selling_time timestamp null comment '上架时间',
	article_num varchar(100) null comment '货号',
	year_to_market varchar(10) null comment '有效期',
	short_des varchar(500) null comment '短描述',
	edit_flag int(4) default 0 null comment '二次编辑标识: 0 一次  1二次 默认为0',
	special_des varchar(500) null comment '特别说明',
	season_code varchar(10) default '1' null comment '是否需要输入版库号  1：需要  2:不需要',
	industry_condition int(4) null comment '业态(0百货,1超市,2电商)',
	pro_type varchar(10) null comment '商品类型',
	long_desc varchar(500) null comment '文本描述',
	sale_type int(1) default 0 null comment '销售类型0线下，1线上 ,2线上线下同款'
)
comment '产品表（SPU）' charset=utf8;

create table if not exists pcm_stock
(
	sid bigint auto_increment comment '库存表SID'
		primary key,
	shoppe_pro_sid varchar(20) null comment '商品编号',
	stock_type_sid int(4) null comment '库存类型SID：1001销售库;1002残次品库;1003退货商品库;1004锁定库',
	pro_sum bigint null comment '商品数量(库存数)',
	channel_sid varchar(10) null comment '渠道sid',
	store_code varchar(20) null comment 'store_code（原来无此数据）',
	sale_type int null,
	data_type int null comment '库存类型 1 条码库存，2 门店SKU总数 '
)
comment '商品库存表' charset=utf8;

create table if not exists pcm_stock_change_record
(
	sid bigint auto_increment
		primary key,
	bills_no varchar(50) null comment '单据号',
	shoppe_pro_sid varchar(20) null comment '专柜商品SID',
	stock_sid bigint null comment '库存表SID',
	change_sum bigint null comment '变动数量',
	change_type_sid int(4) null comment '变动类型SID',
	change_time timestamp default CURRENT_TIMESTAMP not null comment '变动时间',
	note varchar(200) null comment '备注',
	pro_sum bigint default 0 null comment '原库存数',
	field1 varchar(20) null,
	field2 varchar(30) null,
	data_type int null
)
comment '库存变动记录表' charset=utf8;

create table if not exists user_info
(
	id bigint not null
		primary key,
	uid varchar(100) null comment '用户ID',
	username varchar(200) null comment '用户名',
	password varchar(255) null comment '登录密码',
	name varchar(200) null comment '名称',
	mobile varchar(25) null comment '移动电话',
	start_time datetime null comment '有效期开始时间',
	end_time datetime null comment '有效期结束时间',
	employee_id varchar(100) null comment '员工编号',
	sex tinyint(2) null comment '性别 0男1女',
	no_sensitive tinyint(1) null comment '是否脱敏',
	head_pic varchar(400) null comment '头像',
	given_name varchar(20) null comment '名',
	sn varchar(20) null comment '姓',
	email varchar(200) null comment '电子邮件',
	logon_hours datetime null comment '登录时间',
	pwd_last_set datetime null comment '设置密码的时间',
	account_expires datetime null comment '帐户过期',
	enabled tinyint(1) null comment '账号状态： -1 无效 0 锁定 1 普通登录 2 二次验证 3 临时帐号(根据有效期判断）',
	ext_json text null comment '扩展字段（JSON格式）',
	modifier varchar(100) null comment '操作人',
	modified datetime null comment '修改时间',
	create_time datetime null comment '创建时间'
);

