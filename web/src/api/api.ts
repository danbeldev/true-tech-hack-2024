/* eslint-disable */
/* tslint:disable */

/*
 * ---------------------------------------------------------------
 * ## THIS FILE WAS GENERATED VIA SWAGGER-TYPESCRIPT-API        ##
 * ##                                                           ##
 * ## AUTHOR: acacode                                           ##
 * ## SOURCE: https://github.com/acacode/swagger-typescript-api ##
 * ---------------------------------------------------------------
 */

export interface ExceptionModel {
  message?: string;
  code?: string;
}

export interface VenueDto {
  /** @format int32 */
  id: number;
  name: string;
  /** @format double */
  longitude: number;
  /** @format double */
  latitude: number;
  features: ('WHEELCHAIR' | 'BLIND')[];
}

export interface CategoryDto {
  /** @format int32 */
  id: number;
  name: string;
}

export interface EventDto {
  /** @format int32 */
  id: number;
  name: string;
  status: 'ACTUAL';
  image?: string;
  categories: CategoryDto[];
}

export interface EventDetailsDto {
  /** @format int32 */
  id: number;
  name: string;
  description: string;
  status: 'ACTUAL';
  images: string[];
  categories: CategoryDto[];
  schedules: EventScheduleDto[];
  persons: EventPersonDto[];
}

export interface EventPersonDto {
  person: PersonDto;
  post: PersonPostDto;
}

export interface EventScheduleDto {
  /** @format int32 */
  id: number;
  /** @format date-time */
  dateTime: string;
}

export interface PersonDto {
  /** @format int32 */
  id: number;
  firstName: string;
  lastName: string;
  middleName?: string;
  photo?: string;
}

export interface PersonPostDto {
  /** @format int32 */
  id: number;
  name: string;
}

import type {
  AxiosInstance,
  AxiosRequestConfig,
  AxiosResponse,
  HeadersDefaults,
  ResponseType,
} from 'axios';
import axios from 'axios';

export type QueryParamsType = Record<string | number, any>;

export interface FullRequestParams
  extends Omit<AxiosRequestConfig, 'data' | 'params' | 'url' | 'responseType'> {
  /** set parameter to `true` for call `securityWorker` for this request */
  secure?: boolean;
  /** request path */
  path: string;
  /** content type of request body */
  type?: ContentType;
  /** query params */
  query?: QueryParamsType;
  /** format of response (i.e. response.json() -> format: "json") */
  format?: ResponseType;
  /** request body */
  body?: unknown;
}

export type RequestParams = Omit<FullRequestParams, 'body' | 'method' | 'query' | 'path'>;

export interface ApiConfig<SecurityDataType = unknown>
  extends Omit<AxiosRequestConfig, 'data' | 'cancelToken'> {
  securityWorker?: (
    securityData: SecurityDataType | null
  ) => Promise<AxiosRequestConfig | void> | AxiosRequestConfig | void;
  secure?: boolean;
  format?: ResponseType;
}

export enum ContentType {
  Json = 'application/json',
  FormData = 'multipart/form-data',
  UrlEncoded = 'application/x-www-form-urlencoded',
  Text = 'text/plain',
}

export class HttpClient<SecurityDataType = unknown> {
  public instance: AxiosInstance;
  private securityData: SecurityDataType | null = null;
  private securityWorker?: ApiConfig<SecurityDataType>['securityWorker'];
  private secure?: boolean;
  private format?: ResponseType;

  constructor({
    securityWorker,
    secure,
    format,
    ...axiosConfig
  }: ApiConfig<SecurityDataType> = {}) {
    this.instance = axios.create({
      ...axiosConfig,
      baseURL: axiosConfig.baseURL || 'https://api.danbel.ru:30/mtc-live/v1.0',
    });
    this.secure = secure;
    this.format = format;
    this.securityWorker = securityWorker;
  }

  public setSecurityData = (data: SecurityDataType | null) => {
    this.securityData = data;
  };

  public request = async <T = any, _E = any>({
    secure,
    path,
    type,
    query,
    format,
    body,
    ...params
  }: FullRequestParams): Promise<AxiosResponse<T>> => {
    const secureParams =
      ((typeof secure === 'boolean' ? secure : this.secure) &&
        this.securityWorker &&
        (await this.securityWorker(this.securityData))) ||
      {};
    const requestParams = this.mergeRequestParams(params, secureParams);
    const responseFormat = format || this.format || undefined;

    if (type === ContentType.FormData && body && body !== null && typeof body === 'object') {
      body = this.createFormData(body as Record<string, unknown>);
    }

    if (type === ContentType.Text && body && body !== null && typeof body !== 'string') {
      body = JSON.stringify(body);
    }

    return this.instance.request({
      ...requestParams,
      headers: {
        ...(requestParams.headers || {}),
        ...(type && type !== ContentType.FormData ? { 'Content-Type': type } : {}),
      },
      params: query,
      responseType: responseFormat,
      data: body,
      url: path,
    });
  };

  protected mergeRequestParams(
    params1: AxiosRequestConfig,
    params2?: AxiosRequestConfig
  ): AxiosRequestConfig {
    const method = params1.method || (params2 && params2.method);

    return {
      ...this.instance.defaults,
      ...params1,
      ...(params2 || {}),
      headers: {
        ...((method &&
          this.instance.defaults.headers[method.toLowerCase() as keyof HeadersDefaults]) ||
          {}),
        ...(params1.headers || {}),
        ...((params2 && params2.headers) || {}),
      },
    };
  }

  protected stringifyFormItem(formItem: unknown) {
    if (typeof formItem === 'object' && formItem !== null) {
      return JSON.stringify(formItem);
    } else {
      return `${formItem}`;
    }
  }

  protected createFormData(input: Record<string, unknown>): FormData {
    return Object.keys(input || {}).reduce((formData, key) => {
      const property = input[key];
      const propertyContent: any[] = property instanceof Array ? property : [property];

      for (const formItem of propertyContent) {
        const isFileType = formItem instanceof Blob || formItem instanceof File;
        formData.append(key, isFileType ? formItem : this.stringifyFormItem(formItem));
      }

      return formData;
    }, new FormData());
  }
}

/**
 * @title MTC Live API
 * @version 1.0.0
 * @baseUrl https://api.danbel.ru:30/mtc-live/v1.0
 */
export class Api<SecurityDataType extends unknown> extends HttpClient<SecurityDataType> {
  venues = {
    /**
     * No description
     *
     * @tags venue-controller
     * @name GetAll
     * @request GET:/venues
     */
    getAll: (
      query?: {
        features?: ('WHEELCHAIR' | 'BLIND')[];
      },
      params: RequestParams = {}
    ) =>
      this.request<VenueDto[], ExceptionModel>({
        path: `/venues`,
        method: 'GET',
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags venue-controller
     * @name GetAll1
     * @request GET:/venues/search
     */
    getAll1: (
      query: {
        /**
         * Географическая широта центра круга поиска
         * @format double
         */
        lat: number;
        /**
         * Географическая долгота центра круга поиска
         * @format double
         */
        long: number;
        /**
         * Радиус круга поиска
         * @format double
         */
        radios: number;
        features?: ('WHEELCHAIR' | 'BLIND')[];
      },
      params: RequestParams = {}
    ) =>
      this.request<VenueDto[], ExceptionModel>({
        path: `/venues/search`,
        method: 'GET',
        query: query,
        ...params,
      }),
  };
  events = {
    /**
     * No description
     *
     * @tags event-controller
     * @name GetAll2
     * @request GET:/events
     */
    getAll2: (
      query: {
        /** @format int32 */
        venueId: number;
        status: 'ACTUAL';
      },
      params: RequestParams = {}
    ) =>
      this.request<EventDto[], ExceptionModel>({
        path: `/events`,
        method: 'GET',
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags event-controller
     * @name GetById
     * @request GET:/events/{id}
     */
    getById: (id: number, params: RequestParams = {}) =>
      this.request<EventDetailsDto, ExceptionModel>({
        path: `/events/${id}`,
        method: 'GET',
        ...params,
      }),
  };
}

export const api = new Api({
  baseURL: 'https://api.danbel.ru:30/mtc-live/v1.0',
});
