--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

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
-- Name: matches; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE matches (
    id integer NOT NULL,
    name character varying,
    host_org character varying,
    location character varying,
    date character varying,
    athlete_1_name character varying,
    athlete_2_name character varying,
    weight_class numeric,
    athlete_1_belt character varying,
    athlete_2_belt character varying
);


ALTER TABLE matches OWNER TO "Guest";

--
-- Name: matches_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE matches_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE matches_id_seq OWNER TO "Guest";

--
-- Name: matches_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE matches_id_seq OWNED BY matches.id;


--
-- Name: moves; Type: TABLE; Schema: public; Owner: Guest; Tablespace: 
--

CREATE TABLE moves (
    id integer NOT NULL,
    match_id integer,
    name character varying,
    description character varying,
    difficulty character varying
);


ALTER TABLE moves OWNER TO "Guest";

--
-- Name: moves_id_seq; Type: SEQUENCE; Schema: public; Owner: Guest
--

CREATE SEQUENCE moves_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE moves_id_seq OWNER TO "Guest";

--
-- Name: moves_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Guest
--

ALTER SEQUENCE moves_id_seq OWNED BY moves.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY matches ALTER COLUMN id SET DEFAULT nextval('matches_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: Guest
--

ALTER TABLE ONLY moves ALTER COLUMN id SET DEFAULT nextval('moves_id_seq'::regclass);


--
-- Data for Name: matches; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY matches (id, name, host_org, location, date, athlete_1_name, athlete_2_name, weight_class, athlete_1_belt, athlete_2_belt) FROM stdin;
\.


--
-- Name: matches_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('matches_id_seq', 1, false);


--
-- Data for Name: moves; Type: TABLE DATA; Schema: public; Owner: Guest
--

COPY moves (id, match_id, name, description, difficulty) FROM stdin;
\.


--
-- Name: moves_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Guest
--

SELECT pg_catalog.setval('moves_id_seq', 1, false);


--
-- Name: matches_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY matches
    ADD CONSTRAINT matches_pkey PRIMARY KEY (id);


--
-- Name: moves_pkey; Type: CONSTRAINT; Schema: public; Owner: Guest; Tablespace: 
--

ALTER TABLE ONLY moves
    ADD CONSTRAINT moves_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM epicodus;
GRANT ALL ON SCHEMA public TO epicodus;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

