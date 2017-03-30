--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
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


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: matches; Type: TABLE; Schema: public; Owner: mf
--

CREATE TABLE matches (
    id integer NOT NULL,
    name character varying,
    host_org character varying,
    location character varying,
    date character varying,
    athlete_1_name character varying,
    athlete_2_name character varying,
    athlete_1_belt character varying,
    athlete_2_belt character varying,
    weight_class character varying,
    matchurl character varying
);


ALTER TABLE matches OWNER TO mf;

--
-- Name: matches_id_seq; Type: SEQUENCE; Schema: public; Owner: mf
--

CREATE SEQUENCE matches_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE matches_id_seq OWNER TO mf;

--
-- Name: matches_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mf
--

ALTER SEQUENCE matches_id_seq OWNED BY matches.id;


--
-- Name: moves; Type: TABLE; Schema: public; Owner: mf
--

CREATE TABLE moves (
    id integer NOT NULL,
    match_id integer,
    name character varying,
    description character varying,
    difficulty character varying
);


ALTER TABLE moves OWNER TO mf;

--
-- Name: moves_id_seq; Type: SEQUENCE; Schema: public; Owner: mf
--

CREATE SEQUENCE moves_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE moves_id_seq OWNER TO mf;

--
-- Name: moves_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: mf
--

ALTER SEQUENCE moves_id_seq OWNED BY moves.id;


--
-- Name: matches id; Type: DEFAULT; Schema: public; Owner: mf
--

ALTER TABLE ONLY matches ALTER COLUMN id SET DEFAULT nextval('matches_id_seq'::regclass);


--
-- Name: moves id; Type: DEFAULT; Schema: public; Owner: mf
--

ALTER TABLE ONLY moves ALTER COLUMN id SET DEFAULT nextval('moves_id_seq'::regclass);


--
-- Data for Name: matches; Type: TABLE DATA; Schema: public; Owner: mf
--

COPY matches (id, name, host_org, location, date, athlete_1_name, athlete_2_name, athlete_1_belt, athlete_2_belt, weight_class, matchurl) FROM stdin;
\.


--
-- Name: matches_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mf
--

SELECT pg_catalog.setval('matches_id_seq', 10, true);


--
-- Data for Name: moves; Type: TABLE DATA; Schema: public; Owner: mf
--

COPY moves (id, match_id, name, description, difficulty) FROM stdin;
\.


--
-- Name: moves_id_seq; Type: SEQUENCE SET; Schema: public; Owner: mf
--

SELECT pg_catalog.setval('moves_id_seq', 1, false);


--
-- Name: matches matches_pkey; Type: CONSTRAINT; Schema: public; Owner: mf
--

ALTER TABLE ONLY matches
    ADD CONSTRAINT matches_pkey PRIMARY KEY (id);


--
-- Name: moves moves_pkey; Type: CONSTRAINT; Schema: public; Owner: mf
--

ALTER TABLE ONLY moves
    ADD CONSTRAINT moves_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

