--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.12
-- Dumped by pg_dump version 9.5.12

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: journal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.journal (
    id bigint NOT NULL,
    status boolean,
    billno character varying(255),
    billopendate timestamp without time zone,
    recordcount integer,
    voucherdate timestamp without time zone,
    vouchertype character varying(255)
);


ALTER TABLE public.journal OWNER TO postgres;

--
-- Name: journal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.journal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_id_seq OWNER TO postgres;

--
-- Name: journal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.journal_id_seq OWNED BY public.journal.id;


--
-- Name: journal_log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.journal_log (
    id bigint NOT NULL,
    group_id bigint,
    guid character varying(255),
    journal_id bigint,
    reason character varying(255),
    sync_date timestamp without time zone,
    sync_status character varying(255),
    voucher_id character varying(255)
);


ALTER TABLE public.journal_log OWNER TO postgres;

--
-- Name: journal_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.journal_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_log_id_seq OWNER TO postgres;

--
-- Name: journal_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.journal_log_id_seq OWNED BY public.journal_log.id;


--
-- Name: journal_record; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.journal_record (
    id bigint NOT NULL,
    account_group integer,
    account_type character varying(255),
    admitting_department character varying(255),
    admitting_doctor character varying(255),
    audit_control_number character varying(255),
    bill_finalized_date timestamp without time zone,
    bill_no character varying(255),
    bill_open_date timestamp without time zone,
    center_id integer,
    center_name character varying(255),
    charge_group character varying(255),
    charge_head character varying(255),
    conducting_department character varying(255),
    conductiong_doctor character varying(255),
    cost_amount integer,
    counter_no character varying(255),
    credit_account character varying(255),
    currency character varying(255),
    currency_conversion_rate numeric(10,2),
    cust_item_code character varying(255),
    cust_supplier_code character varying(255),
    custom_1 character varying(255),
    custom_10 character varying(255),
    custom_11 character varying(255),
    custom_2 character varying(255),
    custom_3 character varying(255),
    custom_4 character varying(255),
    custom_7 character varying(255),
    custom_8 character varying(255),
    custom_9 character varying(255),
    debit_account character varying(255),
    discount_amount numeric(10,2),
    grn_date character varying(255),
    gross_amount numeric(10,2),
    guid character varying(255),
    incoimng_hospital character varying(255),
    insurance_co character varying(255),
    invoice_date timestamp without time zone,
    invoice_no character varying(255),
    is_tpa character varying(255),
    issue_store character varying(255),
    issue_store_center character varying(255),
    item_category_id integer,
    item_code character varying(255),
    item_name character varying(255),
    journal_id bigint,
    mod_time timestamp without time zone,
    mr_no character varying(255),
    net_amount double precision,
    old_mr_no character varying(255),
    outhouse_name character varying(255),
    payee_doctor character varying(255),
    po_date timestamp without time zone,
    po_number character varying(255),
    points_redeemed integer,
    points_redeemed_amount numeric(10,2),
    points_redeemed_rate numeric(10,2),
    prescribing_doctor character varying(255),
    purchase_vat_amount numeric(10,2),
    purchase_vat_percent numeric(10,2),
    quantity numeric(10,2),
    receipt_store character varying(255),
    receipt_store_center character varying(255),
    referral_doctor character varying(255),
    remarks character varying(255),
    round_off_amount numeric(10,2),
    sales_vat_amount numeric(10,2),
    sales_vat_percent numeric(10,2),
    service_group character varying(255),
    service_sub_group character varying(255),
    supplier_name character varying(255),
    tax_amount numeric(10,2),
    transaction_type character varying(255),
    unit character varying(255),
    unit_rate numeric(10,2),
    update_status integer,
    visit_id character varying(255),
    visit_type character varying(255),
    voucher_date timestamp without time zone,
    voucher_no character varying(255),
    voucher_ref character varying(255),
    voucher_type character varying(255)
);


ALTER TABLE public.journal_record OWNER TO postgres;

--
-- Name: journal_record_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.journal_record_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.journal_record_id_seq OWNER TO postgres;

--
-- Name: journal_record_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.journal_record_id_seq OWNED BY public.journal_record.id;


--
-- Name: zoho_books_config; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.zoho_books_config (
    id bigint NOT NULL,
    auth_token character varying(255),
    organization_id character varying(255)
);


ALTER TABLE public.zoho_books_config OWNER TO postgres;

--
-- Name: zoho_books_config_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.zoho_books_config_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.zoho_books_config_id_seq OWNER TO postgres;

--
-- Name: zoho_books_config_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.zoho_books_config_id_seq OWNED BY public.zoho_books_config.id;


--
-- Name: zoho_chart_of_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.zoho_chart_of_account (
    id bigint NOT NULL,
    account_id character varying(255),
    account_name character varying(255),
    account_type character varying(255),
    description character varying(255)
);


ALTER TABLE public.zoho_chart_of_account OWNER TO postgres;

--
-- Name: zoho_chart_of_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.zoho_chart_of_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.zoho_chart_of_account_id_seq OWNER TO postgres;

--
-- Name: zoho_chart_of_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.zoho_chart_of_account_id_seq OWNED BY public.zoho_chart_of_account.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal ALTER COLUMN id SET DEFAULT nextval('public.journal_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal_log ALTER COLUMN id SET DEFAULT nextval('public.journal_log_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal_record ALTER COLUMN id SET DEFAULT nextval('public.journal_record_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zoho_books_config ALTER COLUMN id SET DEFAULT nextval('public.zoho_books_config_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zoho_chart_of_account ALTER COLUMN id SET DEFAULT nextval('public.zoho_chart_of_account_id_seq'::regclass);


--
-- Name: journal_log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal_log
    ADD CONSTRAINT journal_log_pkey PRIMARY KEY (id);


--
-- Name: journal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal
    ADD CONSTRAINT journal_pkey PRIMARY KEY (id);


--
-- Name: journal_record_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.journal_record
    ADD CONSTRAINT journal_record_pkey PRIMARY KEY (id);


--
-- Name: zoho_books_config_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zoho_books_config
    ADD CONSTRAINT zoho_books_config_pkey PRIMARY KEY (id);


--
-- Name: zoho_chart_of_account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.zoho_chart_of_account
    ADD CONSTRAINT zoho_chart_of_account_pkey PRIMARY KEY (id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

