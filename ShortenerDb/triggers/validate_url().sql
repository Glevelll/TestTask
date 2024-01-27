-- FUNCTION: public.validate_url()

-- DROP FUNCTION IF EXISTS public.validate_url();

CREATE OR REPLACE FUNCTION public.validate_url()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
BEGIN
  IF NEW.url_original IS NOT NULL AND NEW.url_original != '' THEN
    IF LEFT(NEW.url_original, 7) != 'http://' AND LEFT(NEW.url_original, 8) != 'https://' THEN
      RAISE EXCEPTION 'Некорректный URL';
    END IF;
  END IF;
  
  RETURN NEW;
END;
$BODY$;

ALTER FUNCTION public.validate_url()
    OWNER TO postgres;
