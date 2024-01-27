-- Table: public.url

-- DROP TABLE IF EXISTS public.url;

CREATE TABLE IF NOT EXISTS public.url
(
    id bigint NOT NULL DEFAULT 'nextval('url_id_seq'::regclass)',
    url_original character varying(255) COLLATE pg_catalog."default",
    url_short character varying(255) COLLATE pg_catalog."default",
    id_user bigint,
    CONSTRAINT url_pkey PRIMARY KEY (id),
    CONSTRAINT url_url_original_key UNIQUE (url_original),
    CONSTRAINT url_url_short_key UNIQUE (url_short),
    CONSTRAINT url_id_user_fkey FOREIGN KEY (id_user)
        REFERENCES public.account (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.url
    OWNER to postgres;

-- Trigger: validate_url_trigger

-- DROP TRIGGER IF EXISTS validate_url_trigger ON public.url;

CREATE TRIGGER validate_url_trigger
    BEFORE INSERT OR UPDATE 
    ON public.url
    FOR EACH ROW
    EXECUTE FUNCTION public.validate_url();