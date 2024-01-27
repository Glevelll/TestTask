PGDMP     
    '                 |            TestDb    15.2    15.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    19375    TestDb    DATABASE     |   CREATE DATABASE "TestDb" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Russian_Russia.1251';
    DROP DATABASE "TestDb";
                postgres    false            �            1255    19497    validate_url()    FUNCTION     b  CREATE FUNCTION public.validate_url() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  IF NEW.url_original IS NOT NULL AND NEW.url_original != '' THEN
    IF LEFT(NEW.url_original, 7) != 'http://' AND LEFT(NEW.url_original, 8) != 'https://' THEN
      RAISE EXCEPTION 'Некорректный URL';
    END IF;
  END IF;
  
  RETURN NEW;
END;
$$;
 %   DROP FUNCTION public.validate_url();
       public          postgres    false            �            1259    19377    account    TABLE     �   CREATE TABLE public.account (
    id bigint NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);
    DROP TABLE public.account;
       public         heap    postgres    false            �            1259    19376    account_id_seq    SEQUENCE     �   CREATE SEQUENCE public.account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.account_id_seq;
       public          postgres    false    215                       0    0    account_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.account_id_seq OWNED BY public.account.id;
          public          postgres    false    214            �            1259    19434    url    TABLE     �   CREATE TABLE public.url (
    id bigint NOT NULL,
    url_original character varying(255),
    url_short character varying(255),
    id_user bigint
);
    DROP TABLE public.url;
       public         heap    postgres    false            �            1259    19433 
   url_id_seq    SEQUENCE     �   CREATE SEQUENCE public.url_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.url_id_seq;
       public          postgres    false    217                       0    0 
   url_id_seq    SEQUENCE OWNED BY     9   ALTER SEQUENCE public.url_id_seq OWNED BY public.url.id;
          public          postgres    false    216            k           2604    19399 
   account id    DEFAULT     h   ALTER TABLE ONLY public.account ALTER COLUMN id SET DEFAULT nextval('public.account_id_seq'::regclass);
 9   ALTER TABLE public.account ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            l           2604    19451    url id    DEFAULT     `   ALTER TABLE ONLY public.url ALTER COLUMN id SET DEFAULT nextval('public.url_id_seq'::regclass);
 5   ALTER TABLE public.url ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217                      0    19377    account 
   TABLE DATA           9   COPY public.account (id, username, password) FROM stdin;
    public          postgres    false    215   �                 0    19434    url 
   TABLE DATA           C   COPY public.url (id, url_original, url_short, id_user) FROM stdin;
    public          postgres    false    217                     0    0    account_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.account_id_seq', 6, true);
          public          postgres    false    214                       0    0 
   url_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.url_id_seq', 33, true);
          public          postgres    false    216            n           2606    19401    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    215            p           2606    19453    url url_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.url
    ADD CONSTRAINT url_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.url DROP CONSTRAINT url_pkey;
       public            postgres    false    217            r           2606    19443    url url_url_original_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.url
    ADD CONSTRAINT url_url_original_key UNIQUE (url_original);
 B   ALTER TABLE ONLY public.url DROP CONSTRAINT url_url_original_key;
       public            postgres    false    217            t           2606    19445    url url_url_short_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.url
    ADD CONSTRAINT url_url_short_key UNIQUE (url_short);
 ?   ALTER TABLE ONLY public.url DROP CONSTRAINT url_url_short_key;
       public            postgres    false    217            v           2620    19498    url validate_url_trigger    TRIGGER        CREATE TRIGGER validate_url_trigger BEFORE INSERT OR UPDATE ON public.url FOR EACH ROW EXECUTE FUNCTION public.validate_url();
 1   DROP TRIGGER validate_url_trigger ON public.url;
       public          postgres    false    218    217            u           2606    19462    url url_id_user_fkey    FK CONSTRAINT     u   ALTER TABLE ONLY public.url
    ADD CONSTRAINT url_id_user_fkey FOREIGN KEY (id_user) REFERENCES public.account(id);
 >   ALTER TABLE ONLY public.url DROP CONSTRAINT url_id_user_fkey;
       public          postgres    false    217    3182    215               I   x�3�t�IM�442615�2��̍��\&p&B̔3�4'3���� \?OoO=�`G_G�G#�=... �         �   x�}�1�0 ��/�U!!FЎ,	�Ī�D�Ӂ��b�;��D�R���56ooyƋ�J"F����	K��dz8����&�	Ƿ2�:�.D8;Ngu�������{��'�ġ��¦�3�u�}�Q�� ^�A�     